package org.filemodules.file.model.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Files {

    @Builder
    public Files(String originalName, String storedName, String extension, Long size, Long lastModified, LocalDateTime lastModifiedDate, String physicalPath, String mimeType){
        this.originalName = originalName;
        this.storedName = storedName;
        this.extension = extension;
        this.size = size;
        this.lastModified = lastModified;
        this.lastModifiedDate = lastModifiedDate;
        this.physicalPath = physicalPath;
        this.mimeType = mimeType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;
    private String storedName;
    private String extension;
    private Long size;
    private Long lastModified;
    private LocalDateTime lastModifiedDate;
    private String physicalPath;
    private String mimeType;

}
