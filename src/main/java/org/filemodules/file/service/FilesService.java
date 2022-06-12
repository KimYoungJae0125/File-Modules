package org.filemodules.file.service;

import org.filemodules.file.model.dto.FilesDto;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesService {

    public void fileUpload(FilesDto filesDto) {
        Path root = Paths.get(System.getProperty("user.home"), "fileUpload");
//        Path target = root.resolve(filesDto.getOriginalName());
    }
}
