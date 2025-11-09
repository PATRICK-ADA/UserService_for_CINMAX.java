FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/user-service-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV AWS_ACCESS_KEY_ID=""
ENV AWS_SECRET_ACCESS_KEY=""
ENV S3_BUCKET_NAME=""
ENV DB_URL="jdbc:postgresql://localhost:5432/cinmax_db"
ENV DB_USER="postgres"
ENV DB_PASSWORD=""

CMD ["java", "-jar", "app.jar"]
