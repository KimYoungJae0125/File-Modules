package org.filemodules.file.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class FilesDto {

    private String name;
    private String extension;
    private String mimeType;
    private Long size;
    private Long lastModified;
    private LocalDateTime lastModifiedDate;
    private String physicalPath;

    private String fileData;


}
