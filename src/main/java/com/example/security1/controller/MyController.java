package com.example.security1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/witaj")
    public String witaj() {
        return "Strona dla wszystkich";
    }

    @GetMapping("/admin")
    public String dlaAdmina() {
        return "Tylko dla admina";
    }

    @GetMapping("/user")
    public String dlaUsera() {
        return "Tylko dla usera";
    }


}