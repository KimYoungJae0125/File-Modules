package org.filemodules.unit;

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
import org.springframework.context.MessageSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilesTest {
    @Autowired
    MessageSource messageSource;

    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
         FilesTestUtils filesUtils = new FilesTestUtils(messageSource);

         FilesTestDto filesDto = new FilesTestDto(filesUtils);

        filesUtils.deleteFile();
        filesUtils.deleteDirectory();
    }

    @Test
    void createFile(){
        FilesTestUtils filesUtils = new FilesTestUtils(messageSource);

        FilesTestDto filesDto = new FilesTestDto(filesUtils);

        FilesDto fileDto = filesDto.createNormalDto();

        assertThat(fileDto.getExtension(), is("txt"));
        assertThat(fileDto.getMimeType(), is("text/plain"));
        assertThat(fileDto.getOriginalName(), is("test.txt"));


        Files files = modelMapper.map(fileDto, Files.class);

        filesRepository.save(files);
    }

}
