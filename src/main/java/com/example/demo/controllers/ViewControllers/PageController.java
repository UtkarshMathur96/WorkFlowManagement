package com.example.demo.controllers.ViewControllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("appName", appName);
        return "students";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("appName", appName);
        return "products";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("appName", appName);
        return "users";
    }
    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("appName", appName);
        return "orders";
    }
    @GetMapping("/orderPage")
    public String orderPage(Model model) {
        model.addAttribute("appName", appName);
        return "orderPage";
    }
}
