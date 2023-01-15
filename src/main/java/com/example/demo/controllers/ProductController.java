package com.example.demo.controllers;

import com.example.demo.constants.Constants;
import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = Constants.PRODUCT_PATH)
public class ProductController {
    @Autowired // This means to get the bean called userRepository
    private ProductRepository productRepository;

    @PostMapping(path = Constants.ADD) // Map ONLY POST Requests
    public @ResponseBody
    String addProduct(@RequestParam String name
            , @RequestParam Long price) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productRepository.save(p);
        return "Product Created";
    }

    @GetMapping(path = Constants.ALL)
    public @ResponseBody
    Iterable<Product> getAllProducts() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }
}
