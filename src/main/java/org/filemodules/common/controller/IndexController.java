package org.filemodules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

    @GetMapping
    public void index(HttpServletResponse response){
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/index")
    public String index(){
        return "/index";
    }

}
