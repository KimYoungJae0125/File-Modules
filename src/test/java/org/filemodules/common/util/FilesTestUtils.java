package org.filemodules.common.util;

import lombok.RequiredArgsConstructor;
import org.filemodules.common.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.time.LocalDateTime.now;

@Component
public class FilesTestUtils {

    private MessageSource messageSource;

    public FilesTestUtils(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public ResponseMessage createDirectory() {
        Path directoryPath = directoryPath();
        HttpStatus status = null;
        String messageKey = "";
        try {
            if(!Files.isDirectory(directoryPath)){
                Files.createDirectories(directoryPath);
            }
            status = HttpStatus.CREATED;
            messageKey = "directory.create.success";
        } catch (IOException e) {
            status = HttpStatus.FORBIDDEN;
            messageKey = "directory.create.fail";
        }
        return ResponseMessage.builder()
                           .transactionTime(now())
                           .statusCode(status.value())
                           .responseMessage(status.getReasonPhrase())
                           .description(getMessage(messageKey))
                           .data(directoryPath)
                           .build();
    }
    public ResponseMessage createFile() {
        Path filePath = filePath();
        HttpStatus status = null;
        String messageKey = "";
        try {
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
            status = HttpStatus.CREATED;
            messageKey = "file.create.success";
        } catch (IOException e) {
            status = HttpStatus.FORBIDDEN;
            messageKey = "file.create.fail";
        }
        return ResponseMessage.builder()
                           .transactionTime(now())
                           .statusCode(status.value())
                           .responseMessage(status.getReasonPhrase())
                           .description(getMessage(messageKey))
                           .data(filePath)
                           .build();
    }

    public ResponseMessage deleteFile() {
        Path filePath = filePath();
        HttpStatus status = null;
        String messageKey = "";
        try {
            if(Files.deleteIfExists(filePath)){
                status = HttpStatus.OK;
                messageKey = "file.delete.success";
            } else {
                status = HttpStatus.NOT_FOUND;
                messageKey = "file.delete.fail.notFound";
            }
        } catch (IOException e) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            messageKey = "file.delete.fail.server";
        }

        return ResponseMessage.builder()
                              .transactionTime(now())
                              .statusCode(status.value())
                              .responseMessage(status.getReasonPhrase())
                              .description(getMessage(messageKey))
                              .data(filePath)
                              .build();
    }
    public ResponseMessage deleteDirectory() {
        Path directoryPath = directoryPath();
        HttpStatus status = null;
        String messageKey = "";
        try {
            if (Files.deleteIfExists(directoryPath)) {
                status = HttpStatus.OK;
                messageKey = "directory.delete.success";
            } else {
                status = HttpStatus.NOT_FOUND;
                messageKey = "directory.delete.fail.notFound";
            }
        } catch (IOException e) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            messageKey = "directory.delete.fail.server";
        }

        return ResponseMessage.builder()
                              .transactionTime(now())
                              .statusCode(status.value())
                              .responseMessage(status.getReasonPhrase())
                              .description(getMessage(messageKey))
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

    private String getMessage(String key){
        return getMessage(key, null);
    }

    private String getMessage(String key, Object[] args){
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

}
