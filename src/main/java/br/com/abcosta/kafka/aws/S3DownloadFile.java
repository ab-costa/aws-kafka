package br.com.abcosta.kafka.aws;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class S3DownloadFile {

    public static void downloadFile(String fileName) throws IOException {
        String bucket = "abcosta-bucket";
        String key = fileName;
    

        S3Client client = S3Client.builder().build();
         
        GetObjectRequest request = GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();
         
        ResponseInputStream<GetObjectResponse> response = client.getObject(request);
        
        System.out.println(response);
        readFile(response);
                 
    //     BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(key));
         
    //     byte[] buffer = new byte[4096];
    //     int bytesRead = -1;
         
    //     while ((bytesRead = response.read(buffer)) !=  -1) {
    //         outputStream.write(buffer, 0, bytesRead);
    //     }
                             
        response.close();
        // outputStream.close();
    }

    public static void readFile(ResponseInputStream<GetObjectResponse> file)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        System.out.println(">>> MÉTODO READ FILE");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
