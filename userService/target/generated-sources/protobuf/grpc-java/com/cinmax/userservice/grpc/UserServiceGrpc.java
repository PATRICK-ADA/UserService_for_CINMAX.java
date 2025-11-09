package com.cinmax.userservice.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: user.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = com.cinmax.userservice.grpc.CreateUserRequest.class,
      responseType = com.cinmax.userservice.grpc.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getCreateUserMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateUserRequest, com.cinmax.userservice.grpc.UserResponse> getCreateUserMethod;
    if ((getCreateUserMethod = UserServiceGrpc.getCreateUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getCreateUserMethod = UserServiceGrpc.getCreateUserMethod) == null) {
          UserServiceGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.CreateUserRequest, com.cinmax.userservice.grpc.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.CreateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("CreateUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUser",
      requestType = com.cinmax.userservice.grpc.GetUserRequest.class,
      responseType = com.cinmax.userservice.grpc.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getGetUserMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetUserRequest, com.cinmax.userservice.grpc.UserResponse> getGetUserMethod;
    if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserMethod = UserServiceGrpc.getGetUserMethod) == null) {
          UserServiceGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.GetUserRequest, com.cinmax.userservice.grpc.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.GetUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetUser"))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UpdateUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getUpdateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUser",
      requestType = com.cinmax.userservice.grpc.UpdateUserRequest.class,
      responseType = com.cinmax.userservice.grpc.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UpdateUserRequest,
      com.cinmax.userservice.grpc.UserResponse> getUpdateUserMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UpdateUserRequest, com.cinmax.userservice.grpc.UserResponse> getUpdateUserMethod;
    if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
          UserServiceGrpc.getUpdateUserMethod = getUpdateUserMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.UpdateUserRequest, com.cinmax.userservice.grpc.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UpdateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("UpdateUser"))
              .build();
        }
      }
    }
    return getUpdateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.DeleteUserRequest,
      com.cinmax.userservice.grpc.DeleteUserResponse> getDeleteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUser",
      requestType = com.cinmax.userservice.grpc.DeleteUserRequest.class,
      responseType = com.cinmax.userservice.grpc.DeleteUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.DeleteUserRequest,
      com.cinmax.userservice.grpc.DeleteUserResponse> getDeleteUserMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.DeleteUserRequest, com.cinmax.userservice.grpc.DeleteUserResponse> getDeleteUserMethod;
    if ((getDeleteUserMethod = UserServiceGrpc.getDeleteUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getDeleteUserMethod = UserServiceGrpc.getDeleteUserMethod) == null) {
          UserServiceGrpc.getDeleteUserMethod = getDeleteUserMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.DeleteUserRequest, com.cinmax.userservice.grpc.DeleteUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.DeleteUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.DeleteUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("DeleteUser"))
              .build();
        }
      }
    }
    return getDeleteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListUsersRequest,
      com.cinmax.userservice.grpc.ListUsersResponse> getListUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListUsers",
      requestType = com.cinmax.userservice.grpc.ListUsersRequest.class,
      responseType = com.cinmax.userservice.grpc.ListUsersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListUsersRequest,
      com.cinmax.userservice.grpc.ListUsersResponse> getListUsersMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListUsersRequest, com.cinmax.userservice.grpc.ListUsersResponse> getListUsersMethod;
    if ((getListUsersMethod = UserServiceGrpc.getListUsersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListUsersMethod = UserServiceGrpc.getListUsersMethod) == null) {
          UserServiceGrpc.getListUsersMethod = getListUsersMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.ListUsersRequest, com.cinmax.userservice.grpc.ListUsersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListUsersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListUsersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("ListUsers"))
              .build();
        }
      }
    }
    return getListUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateTicketRequest,
      com.cinmax.userservice.grpc.TicketResponse> getCreateTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTicket",
      requestType = com.cinmax.userservice.grpc.CreateTicketRequest.class,
      responseType = com.cinmax.userservice.grpc.TicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateTicketRequest,
      com.cinmax.userservice.grpc.TicketResponse> getCreateTicketMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.CreateTicketRequest, com.cinmax.userservice.grpc.TicketResponse> getCreateTicketMethod;
    if ((getCreateTicketMethod = UserServiceGrpc.getCreateTicketMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getCreateTicketMethod = UserServiceGrpc.getCreateTicketMethod) == null) {
          UserServiceGrpc.getCreateTicketMethod = getCreateTicketMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.CreateTicketRequest, com.cinmax.userservice.grpc.TicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.CreateTicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.TicketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("CreateTicket"))
              .build();
        }
      }
    }
    return getCreateTicketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListTicketsRequest,
      com.cinmax.userservice.grpc.ListTicketsResponse> getListTicketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTickets",
      requestType = com.cinmax.userservice.grpc.ListTicketsRequest.class,
      responseType = com.cinmax.userservice.grpc.ListTicketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListTicketsRequest,
      com.cinmax.userservice.grpc.ListTicketsResponse> getListTicketsMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListTicketsRequest, com.cinmax.userservice.grpc.ListTicketsResponse> getListTicketsMethod;
    if ((getListTicketsMethod = UserServiceGrpc.getListTicketsMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListTicketsMethod = UserServiceGrpc.getListTicketsMethod) == null) {
          UserServiceGrpc.getListTicketsMethod = getListTicketsMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.ListTicketsRequest, com.cinmax.userservice.grpc.ListTicketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTickets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListTicketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListTicketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("ListTickets"))
              .build();
        }
      }
    }
    return getListTicketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMovieRequest,
      com.cinmax.userservice.grpc.MovieResponse> getUploadMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadMovie",
      requestType = com.cinmax.userservice.grpc.UploadMovieRequest.class,
      responseType = com.cinmax.userservice.grpc.MovieResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMovieRequest,
      com.cinmax.userservice.grpc.MovieResponse> getUploadMovieMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMovieRequest, com.cinmax.userservice.grpc.MovieResponse> getUploadMovieMethod;
    if ((getUploadMovieMethod = UserServiceGrpc.getUploadMovieMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUploadMovieMethod = UserServiceGrpc.getUploadMovieMethod) == null) {
          UserServiceGrpc.getUploadMovieMethod = getUploadMovieMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.UploadMovieRequest, com.cinmax.userservice.grpc.MovieResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UploadMovieRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.MovieResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("UploadMovie"))
              .build();
        }
      }
    }
    return getUploadMovieMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMoviesRequest,
      com.cinmax.userservice.grpc.UploadMoviesResponse> getUploadMoviesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadMovies",
      requestType = com.cinmax.userservice.grpc.UploadMoviesRequest.class,
      responseType = com.cinmax.userservice.grpc.UploadMoviesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMoviesRequest,
      com.cinmax.userservice.grpc.UploadMoviesResponse> getUploadMoviesMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.UploadMoviesRequest, com.cinmax.userservice.grpc.UploadMoviesResponse> getUploadMoviesMethod;
    if ((getUploadMoviesMethod = UserServiceGrpc.getUploadMoviesMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUploadMoviesMethod = UserServiceGrpc.getUploadMoviesMethod) == null) {
          UserServiceGrpc.getUploadMoviesMethod = getUploadMoviesMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.UploadMoviesRequest, com.cinmax.userservice.grpc.UploadMoviesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadMovies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UploadMoviesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.UploadMoviesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("UploadMovies"))
              .build();
        }
      }
    }
    return getUploadMoviesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListMoviesRequest,
      com.cinmax.userservice.grpc.ListMoviesResponse> getListMoviesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMovies",
      requestType = com.cinmax.userservice.grpc.ListMoviesRequest.class,
      responseType = com.cinmax.userservice.grpc.ListMoviesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListMoviesRequest,
      com.cinmax.userservice.grpc.ListMoviesResponse> getListMoviesMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.ListMoviesRequest, com.cinmax.userservice.grpc.ListMoviesResponse> getListMoviesMethod;
    if ((getListMoviesMethod = UserServiceGrpc.getListMoviesMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListMoviesMethod = UserServiceGrpc.getListMoviesMethod) == null) {
          UserServiceGrpc.getListMoviesMethod = getListMoviesMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.ListMoviesRequest, com.cinmax.userservice.grpc.ListMoviesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMovies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListMoviesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.ListMoviesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("ListMovies"))
              .build();
        }
      }
    }
    return getListMoviesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetMovieStreamRequest,
      com.cinmax.userservice.grpc.MovieStreamResponse> getGetMovieStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMovieStream",
      requestType = com.cinmax.userservice.grpc.GetMovieStreamRequest.class,
      responseType = com.cinmax.userservice.grpc.MovieStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetMovieStreamRequest,
      com.cinmax.userservice.grpc.MovieStreamResponse> getGetMovieStreamMethod() {
    io.grpc.MethodDescriptor<com.cinmax.userservice.grpc.GetMovieStreamRequest, com.cinmax.userservice.grpc.MovieStreamResponse> getGetMovieStreamMethod;
    if ((getGetMovieStreamMethod = UserServiceGrpc.getGetMovieStreamMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetMovieStreamMethod = UserServiceGrpc.getGetMovieStreamMethod) == null) {
          UserServiceGrpc.getGetMovieStreamMethod = getGetMovieStreamMethod =
              io.grpc.MethodDescriptor.<com.cinmax.userservice.grpc.GetMovieStreamRequest, com.cinmax.userservice.grpc.MovieStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMovieStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.GetMovieStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cinmax.userservice.grpc.MovieStreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetMovieStream"))
              .build();
        }
      }
    }
    return getGetMovieStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createUser(com.cinmax.userservice.grpc.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    default void getUser(com.cinmax.userservice.grpc.GetUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    default void updateUser(com.cinmax.userservice.grpc.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
    }

    /**
     */
    default void deleteUser(com.cinmax.userservice.grpc.DeleteUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.DeleteUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserMethod(), responseObserver);
    }

    /**
     */
    default void listUsers(com.cinmax.userservice.grpc.ListUsersRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListUsersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListUsersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Ticket-related methods
     * </pre>
     */
    default void createTicket(com.cinmax.userservice.grpc.CreateTicketRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.TicketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTicketMethod(), responseObserver);
    }

    /**
     */
    default void listTickets(com.cinmax.userservice.grpc.ListTicketsRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListTicketsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTicketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Movie-related methods
     * </pre>
     */
    default void uploadMovie(com.cinmax.userservice.grpc.UploadMovieRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadMovieMethod(), responseObserver);
    }

    /**
     */
    default void uploadMovies(com.cinmax.userservice.grpc.UploadMoviesRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UploadMoviesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadMoviesMethod(), responseObserver);
    }

    /**
     */
    default void listMovies(com.cinmax.userservice.grpc.ListMoviesRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListMoviesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMoviesMethod(), responseObserver);
    }

    /**
     */
    default void getMovieStream(com.cinmax.userservice.grpc.GetMovieStreamRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieStreamResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMovieStreamMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UserService.
   */
  public static abstract class UserServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UserServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UserService.
   */
  public static final class UserServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void createUser(com.cinmax.userservice.grpc.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUser(com.cinmax.userservice.grpc.GetUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUser(com.cinmax.userservice.grpc.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUser(com.cinmax.userservice.grpc.DeleteUserRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.DeleteUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listUsers(com.cinmax.userservice.grpc.ListUsersRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListUsersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Ticket-related methods
     * </pre>
     */
    public void createTicket(com.cinmax.userservice.grpc.CreateTicketRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.TicketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listTickets(com.cinmax.userservice.grpc.ListTicketsRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListTicketsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListTicketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Movie-related methods
     * </pre>
     */
    public void uploadMovie(com.cinmax.userservice.grpc.UploadMovieRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void uploadMovies(com.cinmax.userservice.grpc.UploadMoviesRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UploadMoviesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadMoviesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listMovies(com.cinmax.userservice.grpc.ListMoviesRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListMoviesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMoviesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMovieStream(com.cinmax.userservice.grpc.GetMovieStreamRequest request,
        io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieStreamResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMovieStreamMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UserService.
   */
  public static final class UserServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.cinmax.userservice.grpc.UserResponse createUser(com.cinmax.userservice.grpc.CreateUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.UserResponse getUser(com.cinmax.userservice.grpc.GetUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.UserResponse updateUser(com.cinmax.userservice.grpc.UpdateUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.DeleteUserResponse deleteUser(com.cinmax.userservice.grpc.DeleteUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.ListUsersResponse listUsers(com.cinmax.userservice.grpc.ListUsersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListUsersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Ticket-related methods
     * </pre>
     */
    public com.cinmax.userservice.grpc.TicketResponse createTicket(com.cinmax.userservice.grpc.CreateTicketRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTicketMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.ListTicketsResponse listTickets(com.cinmax.userservice.grpc.ListTicketsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTicketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Movie-related methods
     * </pre>
     */
    public com.cinmax.userservice.grpc.MovieResponse uploadMovie(com.cinmax.userservice.grpc.UploadMovieRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadMovieMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.UploadMoviesResponse uploadMovies(com.cinmax.userservice.grpc.UploadMoviesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadMoviesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.ListMoviesResponse listMovies(com.cinmax.userservice.grpc.ListMoviesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMoviesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cinmax.userservice.grpc.MovieStreamResponse getMovieStream(com.cinmax.userservice.grpc.GetMovieStreamRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMovieStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UserService.
   */
  public static final class UserServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.UserResponse> createUser(
        com.cinmax.userservice.grpc.CreateUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.UserResponse> getUser(
        com.cinmax.userservice.grpc.GetUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.UserResponse> updateUser(
        com.cinmax.userservice.grpc.UpdateUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.DeleteUserResponse> deleteUser(
        com.cinmax.userservice.grpc.DeleteUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.ListUsersResponse> listUsers(
        com.cinmax.userservice.grpc.ListUsersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListUsersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Ticket-related methods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.TicketResponse> createTicket(
        com.cinmax.userservice.grpc.CreateTicketRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.ListTicketsResponse> listTickets(
        com.cinmax.userservice.grpc.ListTicketsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListTicketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Movie-related methods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.MovieResponse> uploadMovie(
        com.cinmax.userservice.grpc.UploadMovieRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadMovieMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.UploadMoviesResponse> uploadMovies(
        com.cinmax.userservice.grpc.UploadMoviesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadMoviesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.ListMoviesResponse> listMovies(
        com.cinmax.userservice.grpc.ListMoviesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMoviesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cinmax.userservice.grpc.MovieStreamResponse> getMovieStream(
        com.cinmax.userservice.grpc.GetMovieStreamRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMovieStreamMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_GET_USER = 1;
  private static final int METHODID_UPDATE_USER = 2;
  private static final int METHODID_DELETE_USER = 3;
  private static final int METHODID_LIST_USERS = 4;
  private static final int METHODID_CREATE_TICKET = 5;
  private static final int METHODID_LIST_TICKETS = 6;
  private static final int METHODID_UPLOAD_MOVIE = 7;
  private static final int METHODID_UPLOAD_MOVIES = 8;
  private static final int METHODID_LIST_MOVIES = 9;
  private static final int METHODID_GET_MOVIE_STREAM = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((com.cinmax.userservice.grpc.CreateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse>) responseObserver);
          break;
        case METHODID_GET_USER:
          serviceImpl.getUser((com.cinmax.userservice.grpc.GetUserRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse>) responseObserver);
          break;
        case METHODID_UPDATE_USER:
          serviceImpl.updateUser((com.cinmax.userservice.grpc.UpdateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UserResponse>) responseObserver);
          break;
        case METHODID_DELETE_USER:
          serviceImpl.deleteUser((com.cinmax.userservice.grpc.DeleteUserRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.DeleteUserResponse>) responseObserver);
          break;
        case METHODID_LIST_USERS:
          serviceImpl.listUsers((com.cinmax.userservice.grpc.ListUsersRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListUsersResponse>) responseObserver);
          break;
        case METHODID_CREATE_TICKET:
          serviceImpl.createTicket((com.cinmax.userservice.grpc.CreateTicketRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.TicketResponse>) responseObserver);
          break;
        case METHODID_LIST_TICKETS:
          serviceImpl.listTickets((com.cinmax.userservice.grpc.ListTicketsRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListTicketsResponse>) responseObserver);
          break;
        case METHODID_UPLOAD_MOVIE:
          serviceImpl.uploadMovie((com.cinmax.userservice.grpc.UploadMovieRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieResponse>) responseObserver);
          break;
        case METHODID_UPLOAD_MOVIES:
          serviceImpl.uploadMovies((com.cinmax.userservice.grpc.UploadMoviesRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.UploadMoviesResponse>) responseObserver);
          break;
        case METHODID_LIST_MOVIES:
          serviceImpl.listMovies((com.cinmax.userservice.grpc.ListMoviesRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.ListMoviesResponse>) responseObserver);
          break;
        case METHODID_GET_MOVIE_STREAM:
          serviceImpl.getMovieStream((com.cinmax.userservice.grpc.GetMovieStreamRequest) request,
              (io.grpc.stub.StreamObserver<com.cinmax.userservice.grpc.MovieStreamResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.CreateUserRequest,
              com.cinmax.userservice.grpc.UserResponse>(
                service, METHODID_CREATE_USER)))
        .addMethod(
          getGetUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.GetUserRequest,
              com.cinmax.userservice.grpc.UserResponse>(
                service, METHODID_GET_USER)))
        .addMethod(
          getUpdateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.UpdateUserRequest,
              com.cinmax.userservice.grpc.UserResponse>(
                service, METHODID_UPDATE_USER)))
        .addMethod(
          getDeleteUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.DeleteUserRequest,
              com.cinmax.userservice.grpc.DeleteUserResponse>(
                service, METHODID_DELETE_USER)))
        .addMethod(
          getListUsersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.ListUsersRequest,
              com.cinmax.userservice.grpc.ListUsersResponse>(
                service, METHODID_LIST_USERS)))
        .addMethod(
          getCreateTicketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.CreateTicketRequest,
              com.cinmax.userservice.grpc.TicketResponse>(
                service, METHODID_CREATE_TICKET)))
        .addMethod(
          getListTicketsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.ListTicketsRequest,
              com.cinmax.userservice.grpc.ListTicketsResponse>(
                service, METHODID_LIST_TICKETS)))
        .addMethod(
          getUploadMovieMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.UploadMovieRequest,
              com.cinmax.userservice.grpc.MovieResponse>(
                service, METHODID_UPLOAD_MOVIE)))
        .addMethod(
          getUploadMoviesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.UploadMoviesRequest,
              com.cinmax.userservice.grpc.UploadMoviesResponse>(
                service, METHODID_UPLOAD_MOVIES)))
        .addMethod(
          getListMoviesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.ListMoviesRequest,
              com.cinmax.userservice.grpc.ListMoviesResponse>(
                service, METHODID_LIST_MOVIES)))
        .addMethod(
          getGetMovieStreamMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cinmax.userservice.grpc.GetMovieStreamRequest,
              com.cinmax.userservice.grpc.MovieStreamResponse>(
                service, METHODID_GET_MOVIE_STREAM)))
        .build();
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cinmax.userservice.grpc.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UserServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getGetUserMethod())
              .addMethod(getUpdateUserMethod())
              .addMethod(getDeleteUserMethod())
              .addMethod(getListUsersMethod())
              .addMethod(getCreateTicketMethod())
              .addMethod(getListTicketsMethod())
              .addMethod(getUploadMovieMethod())
              .addMethod(getUploadMoviesMethod())
              .addMethod(getListMoviesMethod())
              .addMethod(getGetMovieStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
