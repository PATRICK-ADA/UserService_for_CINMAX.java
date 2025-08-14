package com.example.userservice.grpc;

import io.grpc.stub.StreamObserver;
import com.example.userservice.grpc.UserServiceGrpc;
import com.example.userservice.model.User;
import com.example.userservice.model.Ticket;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.TicketRepository;
import java.sql.*;
import java.util.List;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public UserServiceImpl(Connection connection) {
        this.userRepository = new UserRepository(connection);
        this.ticketRepository = new TicketRepository(connection);
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

    // Helper: Convert internal User to proto User
    private com.example.userservice.grpc.User toProtoUser(User user) {
        com.example.userservice.grpc.User.Builder builder = com.example.userservice.grpc.User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail());
        for (Ticket ticket : user.getTickets()) {
            builder.addTickets(toProtoTicket(ticket));
        }
        return builder.build();
    }

    // Helper: Convert internal Ticket to proto Ticket
    private com.example.userservice.grpc.Ticket toProtoTicket(Ticket ticket) {
        return com.example.userservice.grpc.Ticket.newBuilder()
                .setTicketId(ticket.getTicketId())
                .setUserId(ticket.getUserId())
                .setMovieName(ticket.getMovieName())
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
