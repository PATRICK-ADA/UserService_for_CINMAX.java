package com.cinmax.userservice.grpc;

import io.grpc.stub.StreamObserver;
import com.cinmax.userservice.grpc.UserServiceGrpc;
import com.cinmax.userservice.model.User;
import com.cinmax.userservice.model.Ticket;
import com.cinmax.userservice.model.Movie;
import com.cinmax.userservice.model.StreamQuality;
import com.cinmax.userservice.repository.UserRepository;
import com.cinmax.userservice.repository.TicketRepository;
import com.cinmax.userservice.repository.MovieRepository;
import com.cinmax.userservice.service.VideoProcessingService;
import com.cinmax.userservice.service.S3Service;
import software.amazon.awssdk.regions.Region;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final VideoProcessingService videoProcessingService;
    private final S3Service s3Service;

    public UserServiceImpl(Connection connection, String awsAccessKey, String awsSecretKey, String s3BucketName) {
        this.userRepository = new UserRepository(connection);
        this.ticketRepository = new TicketRepository(connection);
        this.movieRepository = new MovieRepository(connection);
        this.videoProcessingService = new VideoProcessingService();
        this.s3Service = new S3Service(awsAccessKey, awsSecretKey, s3BucketName, Region.US_EAST_1);
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            User user = new User(null, request.getName(), request.getEmail());
            user = userRepository.save(user);
            UserResponse response = UserResponse.newBuilder().setUser(toProtoUser(user)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            User user = userRepository.findById(request.getId());
            if (user == null) {
                responseObserver.onError(new Exception("User not found"));
                return;
            }
            // Load tickets for user
            List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
            user.setTickets(tickets);
            UserResponse response = UserResponse.newBuilder().setUser(toProtoUser(user)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            User user = userRepository.findById(request.getId());
            if (user == null) {
                responseObserver.onError(new Exception("User not found"));
                return;
            }
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            userRepository.update(user);
            // Load tickets for user
            List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
            user.setTickets(tickets);
            UserResponse response = UserResponse.newBuilder().setUser(toProtoUser(user)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        try {
            boolean removed = userRepository.delete(request.getId());
            DeleteUserResponse response = DeleteUserResponse.newBuilder().setSuccess(removed).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void listUsers(ListUsersRequest request, StreamObserver<ListUsersResponse> responseObserver) {
        try {
            List<User> users = userRepository.findAll();
            ListUsersResponse.Builder builder = ListUsersResponse.newBuilder();
            for (User user : users) {
                // Load tickets for each user
                List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
                user.setTickets(tickets);
                builder.addUsers(toProtoUser(user));
            }
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void createTicket(CreateTicketRequest request, StreamObserver<TicketResponse> responseObserver) {
        try {
            User user = userRepository.findById(request.getUserId());
            if (user == null) {
                responseObserver.onError(new Exception("User not found"));
                return;
            }
            String ticketId = generateTicketId(user.getName(), request.getMovieName());
            // No need to check for uniqueness, ticketId is PK, let DB handle it
            Ticket ticket = new Ticket(ticketId, user.getId(), request.getMovieName());
            ticketRepository.save(ticket);
            TicketResponse response = TicketResponse.newBuilder().setTicket(toProtoTicket(ticket)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void listTickets(ListTicketsRequest request, StreamObserver<ListTicketsResponse> responseObserver) {
        try {
            User user = userRepository.findById(request.getUserId());
            if (user == null) {
                responseObserver.onError(new Exception("User not found"));
                return;
            }
            List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
            ListTicketsResponse.Builder builder = ListTicketsResponse.newBuilder();
            for (Ticket ticket : tickets) {
                builder.addTickets(toProtoTicket(ticket));
            }
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void uploadMovie(UploadMovieRequest request, StreamObserver<MovieResponse> responseObserver) {
        try {
            // Upload to S3
            byte[] fileData = request.getFileData().toByteArray();
            String s3Url = s3Service.uploadMovie(fileData, request.getFileName(), request.getContentType());
            
            Movie movie = new Movie(null, request.getTitle(), request.getDescription(),
                s3Url, request.getFileName(), request.getContentType(), fileData.length);
            movie = movieRepository.save(movie);

            // Process video for adaptive streaming in background
            videoProcessingService.processVideoForStreaming(movie);
            movieRepository.updateStreamingInfo(movie.getId(), movie.getMasterPlaylistUrl(), movie.getQualities());

            MovieResponse response = MovieResponse.newBuilder().setMovie(toProtoMovie(movie)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void uploadMovies(UploadMoviesRequest request, StreamObserver<UploadMoviesResponse> responseObserver) {
        try {
            List<Movie> uploadedMovies = new ArrayList<>();
            for (UploadMovieRequest movieRequest : request.getMoviesList()) {
                // Upload to S3
                byte[] fileData = movieRequest.getFileData().toByteArray();
                String s3Url = s3Service.uploadMovie(fileData, movieRequest.getFileName(), movieRequest.getContentType());
                
                Movie movie = new Movie(null, movieRequest.getTitle(), movieRequest.getDescription(),
                    s3Url, movieRequest.getFileName(), movieRequest.getContentType(), fileData.length);
                movie = movieRepository.save(movie);

                // Process video for adaptive streaming
                videoProcessingService.processVideoForStreaming(movie);
                movieRepository.updateStreamingInfo(movie.getId(), movie.getMasterPlaylistUrl(), movie.getQualities());

                uploadedMovies.add(movie);
            }
            UploadMoviesResponse.Builder builder = UploadMoviesResponse.newBuilder()
                .setUploadedCount(uploadedMovies.size());
            for (Movie movie : uploadedMovies) {
                builder.addMovies(toProtoMovie(movie));
            }
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void listMovies(ListMoviesRequest request, StreamObserver<ListMoviesResponse> responseObserver) {
        try {
            int page = request.getPage() > 0 ? request.getPage() : 1;
            int size = request.getSize() > 0 ? request.getSize() : 10;

            List<Movie> movies = movieRepository.findAllPaginated(page, size);
            long totalElements = movieRepository.countAll();
            int totalPages = (int) Math.ceil((double) totalElements / size);

            ListMoviesResponse.Builder builder = ListMoviesResponse.newBuilder();
            for (Movie movie : movies) {
                builder.addMovies(toProtoMovie(movie));
            }
            builder.setCurrentPage(page)
                   .setPageSize(size)
                   .setTotalPages(totalPages)
                   .setTotalElements(totalElements);

            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getMovieStream(GetMovieStreamRequest request, StreamObserver<MovieStreamResponse> responseObserver) {
        try {
            Movie movie = movieRepository.findById(request.getMovieId());
            if (movie == null) {
                responseObserver.onError(new Exception("Movie not found"));
                return;
            }

            MovieStreamResponse.Builder builder = MovieStreamResponse.newBuilder()
                .setMasterPlaylistUrl(movie.getMasterPlaylistUrl() != null ? movie.getMasterPlaylistUrl() : "")
                .setProcessedForStreaming(movie.isProcessedForStreaming());

            for (StreamQuality quality : movie.getQualities()) {
                builder.addQualities(toProtoStreamQuality(quality));
            }

            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    // Helper: Convert internal User to proto User
    private com.cinmax.userservice.grpc.User toProtoUser(User user) {
        com.cinmax.userservice.grpc.User.Builder builder = com.cinmax.userservice.grpc.User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail());
        for (Ticket ticket : user.getTickets()) {
            builder.addTickets(toProtoTicket(ticket));
        }
        return builder.build();
    }

    // Helper: Convert internal Ticket to proto Ticket
    private com.cinmax.userservice.grpc.Ticket toProtoTicket(Ticket ticket) {
        return com.cinmax.userservice.grpc.Ticket.newBuilder()
                .setTicketId(ticket.getTicketId())
                .setUserId(ticket.getUserId())
                .setMovieName(ticket.getMovieName())
                .build();
    }

    // Helper: Convert internal Movie to proto Movie
    private com.cinmax.userservice.grpc.Movie toProtoMovie(Movie movie) {
        com.cinmax.userservice.grpc.Movie.Builder builder = com.cinmax.userservice.grpc.Movie.newBuilder()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setDescription(movie.getDescription())
                .setS3Url(movie.getS3Url())
                .setFileSize(movie.getFileSize())
                .setFileName(movie.getFileName())
                .setContentType(movie.getContentType())
                .setMasterPlaylistUrl(movie.getMasterPlaylistUrl() != null ? movie.getMasterPlaylistUrl() : "")
                .setProcessedForStreaming(movie.isProcessedForStreaming())
                .setBuyPrice(movie.getBuyPrice())
                .setRentalPrice(movie.getRentalPrice());

        for (StreamQuality quality : movie.getQualities()) {
            builder.addQualities(toProtoStreamQuality(quality));
        }

        return builder.build();
    }

    // Helper: Convert internal StreamQuality to proto StreamQuality
    private com.cinmax.userservice.grpc.StreamQuality toProtoStreamQuality(StreamQuality quality) {
        return com.cinmax.userservice.grpc.StreamQuality.newBuilder()
                .setResolution(quality.getResolution())
                .setBandwidth(quality.getBandwidth())
                .setPlaylistUrl(quality.getPlaylistUrl())
                .setWidth(quality.getWidth())
                .setHeight(quality.getHeight())
                .build();
    }

    // Ticket ID generation algorithm
    private String generateTicketId(String username, String movieName) {
        String userPart = (username.length() >= 3 ? username.substring(0, 3) : username).toUpperCase();
        String moviePart = (movieName.length() >= 3 ? movieName.substring(0, 3) : movieName).toUpperCase();
        int randomNum = (int)(Math.random() * 1_000_000);
        String randomDigits = String.format("%06d", randomNum);
        return userPart + randomDigits + moviePart;
    }
}
