package dontlikenaming.springboot.semiprojectv7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/intro")
    public String intro(){
        return "intro";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "admin";
    }
}
