package br.com.abcosta.kafka.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "upload";
    }
}
