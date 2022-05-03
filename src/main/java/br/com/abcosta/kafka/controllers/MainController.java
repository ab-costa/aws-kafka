package br.com.abcosta.kafka.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.abcosta.kafka.aws.S3DownloadFile;
import br.com.abcosta.kafka.aws.S3UploadFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Controller
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUploadForm(Model model, String description,
            @RequestParam("file") MultipartFile multipart) {
        String fileName = multipart.getOriginalFilename();
         
        System.out.println("Description: " + description);
        System.out.println("filename: " + fileName);
         
        String message = "";
         
        try {
            S3UploadFile.uploadFile(fileName, multipart.getInputStream());
            message = "Your file has been uploaded successfully!";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
         
        model.addAttribute("message", message);
         
        return "message";              
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName, Model model) throws IOException {
        System.out.println(">>>>> DOWNLOAD FILE");
        System.out.println(fileName);

        S3DownloadFile.downloadFile(fileName);
        // String bucket = "abcosta-bucket";
        // String key = fileName;

        // S3Client client = S3Client.builder().build();

        // GetObjectRequest request = GetObjectRequest.builder()
        //                                            .bucket(bucket)
        //                                            .key(key)
        //                                            .build();

        // ResponseInputStream<GetObjectResponse> response = client.getObject(request);

        // BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(key));

        // byte[] buffer = new byte[4096];
        // int bytesRead = -1;
         
        // while ((bytesRead = response.read(buffer)) !=  -1) {
        //     outputStream.write(buffer, 0, bytesRead);
        // }
                             
        // response.close();
        // outputStream.close();

        System.out.println(">>>>> ANTES DE RENDERIZAR A PÁGINA DOWNLOAD");

        model.addAttribute("download", fileName);

        return "download";
    }
}
