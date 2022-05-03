package br.com.abcosta.kafka.aws;

import java.io.IOException;
import java.io.InputStream;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3UploadFile {

    private static final String BUCKET = "abcosta-bucket";
     
    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        S3Client client = S3Client.builder().build();
        System.out.println(">>> UPLOAD FILE");
        PutObjectRequest request = PutObjectRequest.builder()
                            .bucket(BUCKET)
                            .key(fileName)
                            .acl("public-read")
                            .contentType("text/csv")
                            .build();
        System.out.println(">>> UPLOAD FILE");
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        System.out.println(">>> UPLOAD FILE");
    }
}
