package com.scan4kids.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/albums")
    public String albums() {
        return "albums/index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/donate")
    public String donate() {
        return "donate";
    }

    @GetMapping("/albums/photos")
    public String photos() {
        return "albums/photos";
    }
}
