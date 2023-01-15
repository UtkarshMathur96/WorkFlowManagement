package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World from Spring Boot";
    }
}