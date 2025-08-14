package com.example.userservice.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class UserServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 9090;
        // SQL Server JDBC connection
    String url = "jdbc:sqlserver://localhost:1434;databaseName=userservice_db;encrypt=false";
        String user = "sa";
        String password = "Patosky19971994@$";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Server server = ServerBuilder.forPort(port)
                    .addService(new UserServiceImpl(connection))
                    .addService(ProtoReflectionService.newInstance())
                    .build();
            System.out.println("Starting gRPC server on port " + port + "...");
            server.start();
            System.out.println("Server started.");
            server.awaitTermination();
        } catch (SQLException e) {
            System.err.println("Failed to connect to SQL Server: " + e.getMessage());
        }
    }
}
