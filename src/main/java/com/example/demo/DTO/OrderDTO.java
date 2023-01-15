package com.example.demo.DTO;

import java.util.ArrayList;

public class OrderDTO extends DTO {
    String email;
    ArrayList<ProductsDTO> products;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductsDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "email='" + email + '\'' +
                ", productsDTO=" + products +
                '}';
    }
}
