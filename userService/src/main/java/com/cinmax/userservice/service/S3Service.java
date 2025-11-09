package com.cinmax.userservice.service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.util.UUID;

public class S3Service {
    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(String accessKey, String secretKey, String bucketName, Region region) {
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
            .region(region)
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)))
            .build();
    }

    public String uploadMovie(byte[] fileData, String fileName, String contentType) {
        String key = "movies/" + UUID.randomUUID() + "_" + fileName;
        
        PutObjectRequest putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build();

        s3Client.putObject(putRequest, RequestBody.fromBytes(fileData));
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);
    }
}