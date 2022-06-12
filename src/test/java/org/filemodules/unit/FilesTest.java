package org.filemodules.unit;

import org.filemodules.common.dto.FilesTestDto;
import org.filemodules.common.util.FilesTestUtils;
import org.filemodules.file.model.dto.FilesDto;
import org.filemodules.file.model.entity.Files;
import org.filemodules.file.repository.FilesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilesTest {
    private FilesTestUtils filesUtils = new FilesTestUtils();
    private FilesTestDto filesDto = new FilesTestDto();

    @Autowired
    private FilesRepository filesRepository;

    @BeforeEach
    void setUp() {
        filesUtils.deleteFile();
        filesUtils.deleteDirectory();
    }

    @Test
    void createFile(){
        FilesDto fileDto = filesDto.createNormalDto();

        assertThat(fileDto.getExtension(), is("txt"));
        assertThat(fileDto.getMimeType(), is("text/plain"));
        assertThat(fileDto.getName(), is("test.txt"));

        Files files = Files.builder()
                            .originalName(fileDto.getName())
                            .storedName(String.valueOf(UUID.randomUUID()).substring(0, 8) + "." + fileDto.getExtension())
                            .extension(fileDto.getExtension())
                            .size(fileDto.getSize())
                            .physicalPath(fileDto.getPhysicalPath())
                            .lastModifiedDate(fileDto.getLastModifiedDate())
                            .lastModified(fileDto.getLastModified())
                            .mimeType(fileDto.getMimeType())
                            .build();

        filesRepository.save(files);

    }

}
