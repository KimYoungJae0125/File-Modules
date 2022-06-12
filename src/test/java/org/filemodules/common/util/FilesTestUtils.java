package org.filemodules.common.util;

import org.filemodules.common.response.ResponseMessage;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.time.LocalDateTime.now;

public class FilesTestUtils {

    public ResponseMessage createDirectory() {
        Path directoryPath = directoryPath();
        HttpStatus status = null;
        String description = "";
        try {
            if(!Files.isDirectory(directoryPath)){
                Files.createDirectories(directoryPath);
            }
            status = HttpStatus.CREATED;
            description = "success createDirectory";
        } catch (IOException e) {
            status = HttpStatus.FORBIDDEN;
            description = "Fail createDirectory By AccessDenied";
        }
        return ResponseMessage.builder()
                           .transactionTime(now())
                           .statusCode(status.value())
                           .responseMessage(status.getReasonPhrase())
                           .description(description)
                           .data(directoryPath)
                           .build();
    }
    public ResponseMessage createFile() {
        Path filePath = filePath();
        HttpStatus status = null;
        String description = "";
        try {
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
            status = HttpStatus.CREATED;
            description = "Success createFile";
        } catch (IOException e) {
            status = HttpStatus.FORBIDDEN;
            description = "Fail createFile By AccessDenied";
        }
        return ResponseMessage.builder()
                           .transactionTime(now())
                           .statusCode(status.value())
                           .responseMessage(status.getReasonPhrase())
                           .description(description)
                           .data(filePath)
                           .build();
    }

    public ResponseMessage deleteFile() {
        Path filePath = filePath();
        HttpStatus status = null;
        String description = "";
        try {
            if(Files.deleteIfExists(filePath)){
                status = HttpStatus.OK;
                description = "Success deleteFile";
            } else {
                status = HttpStatus.NOT_FOUND;
                description = "Fail deleteFile by NotFound";
            }
        } catch (IOException e) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            description = "Fail deleteFile by Server";
        }

        return ResponseMessage.builder()
                              .transactionTime(now())
                              .statusCode(status.value())
                              .responseMessage(status.getReasonPhrase())
                              .description(description)
                              .data(filePath)
                              .build();
    }
    public ResponseMessage deleteDirectory() {
        Path directoryPath = directoryPath();
        HttpStatus status = null;
        String description = "";
        try {
            if (Files.deleteIfExists(directoryPath)) {
                status = HttpStatus.OK;
                description = "Success deletDirectory";
            } else {
                status = HttpStatus.NOT_FOUND;
                description = "Fail deleteFile by NotFound";
            }
        } catch (IOException e) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            description = "Fail deleteFile by Server";
        }

        return ResponseMessage.builder()
                              .transactionTime(now())
                              .statusCode(status.value())
                              .responseMessage(status.getReasonPhrase())
                              .description(description)
                              .data(directoryPath)
                              .build();
    }


    private Path directoryPath(){
        return Paths.get(System.getProperty("user.home"), "fileUpload");
//        return root.resolve("test.txt");
    }

    private Path filePath() {
        return directoryPath().resolve("test.txt");
    }


    public Path getFilePath() {
        return filePath();
    }

}
