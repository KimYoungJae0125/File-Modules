package org.filemodules.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.filemodules.common.dto.FilesTestDto;
import org.filemodules.common.util.FilesTestUtils;
import org.filemodules.file.model.dto.FilesDto;
import org.filemodules.file.model.entity.Files;
import org.filemodules.file.repository.FilesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilesTest {
    @Autowired
    private FilesTestUtils filesUtils;
    @Autowired
    private FilesTestDto filesDto;

    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private ModelMapper modelMapper;

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
        assertThat(fileDto.getOriginalName(), is("test.txt"));


        Files files = modelMapper.map(fileDto, Files.class);

        filesRepository.save(files);
    }

}
