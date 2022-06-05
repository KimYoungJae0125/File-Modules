package org.filemodules.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @GetMapping("/test")
    public String test(){

        System.out.println("통신 테스트");
        return "Hello";
    }

}
