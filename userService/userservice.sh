#!/bin/bash
# Script to start the Java gRPC UserService project
cd "$(dirname "$0")"
mvn exec:java -Dexec.mainClass="com.example.userservice.grpc.UserServiceServer" -DskipTests
