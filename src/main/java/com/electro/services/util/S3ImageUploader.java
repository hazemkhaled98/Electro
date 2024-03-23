package com.electro.services.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class S3ImageUploader {

    private static final String PROPERTIES_PATH = "/aws.properties";
    private static final Properties AWS_PROPERTIES = loadProperties();

    private static final Regions REGION = Regions.US_EAST_1;

    private S3ImageUploader() {
    }

    public static String uploadImageToS3(Part filePart) throws IOException {
        String fileName = generateUniqueFileName(filePart.getSubmittedFileName());
        String fileUrl = "";

        try {
            final String BUCKET_NAME = AWS_PROPERTIES.getProperty("bucketName");
            final String ACCESS_KEY = AWS_PROPERTIES.getProperty("accessKey");
            final String SECRET_KEY = AWS_PROPERTIES.getProperty("secretKey");

            BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(REGION)
                    .build();

            InputStream inputStream = filePart.getInputStream();
            PutObjectResult result = s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, inputStream, null));

            // Generate S3 URL
            if (result != null) {
                fileUrl = "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return fileUrl;
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try (InputStream input = S3ImageUploader.class.getResourceAsStream(S3ImageUploader.PROPERTIES_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static String generateUniqueFileName(String originalFileName) {
        String[] parts = originalFileName.split("\\.");
        String extension = parts[parts.length - 1];
        return UUID.randomUUID().toString() + "." + extension;
    }
}
