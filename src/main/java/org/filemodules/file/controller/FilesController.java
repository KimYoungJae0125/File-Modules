package org.filemodules.file.controller;

import org.filemodules.file.model.dto.FilesDto;
import org.filemodules.file.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/files")
public class FilesController {

    private final FilesService fileService;

    @Autowired
    public FilesController(FilesService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String test(){

        System.out.println("통신 테스트");
        return "Hello";
    }

    @PostMapping
    public void fileUpload(FilesDto filesDto) {
        fileService.fileUpload(filesDto);

    }


}
