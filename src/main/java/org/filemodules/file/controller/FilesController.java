package org.filemodules.file.controller;

import org.filemodules.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/test")
    public String test(){

        System.out.println("통신 테스트");
        return "Hello";
    }

    @PostMapping("/upload")
    public void fileUpload(HttpServletRequest request) {
        fileService.fileUpload(request);

    }


}
