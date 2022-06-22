package org.filemodules.common.dto;

import org.filemodules.common.response.ResponseMessage;
import org.filemodules.common.util.FilesTestUtils;
import org.filemodules.file.model.dto.FilesDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.time.LocalDateTime.now;

public class FilesTestDto {

    private final FilesTestUtils filesUtils;

    public FilesTestDto(FilesTestUtils filesUtils){
        this.filesUtils =  filesUtils;
    }

    public FilesDto createNormalDto(){
        filesUtils.createDirectory();
        ResponseMessage fileMessage = filesUtils.createFile();

        Path filePath = (Path) fileMessage.getData();
        String fileName = "";
        String mimeType = "";
        String extension = "";
        Long fileSize = 0L;
        try {
            fileName = String.valueOf(filePath.getFileName());
            extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
            mimeType = Files.probeContentType(filePath);
            fileSize = Files.size(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(fileMessage.getDescription());

        return FilesDto.builder()
                       .originalName(fileName)
                       .storedName(String.valueOf(UUID.randomUUID()).substring(0, 8) + "." + extension)
                       .extension(extension)
                       .mimeType(mimeType)
                       .size(fileSize)
                       .physicalPath(String.valueOf(filePath))
                       .lastModifiedDate(now())
                       .build();


    }

}
