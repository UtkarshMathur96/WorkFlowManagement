package com.example.demo.controllers.services;

import com.example.demo.DTO.ProductsDTO;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ProductService {
    void addProductsToOrder(Order order, ArrayList<ProductsDTO> products);

    Collection<OrderProduct> getProductsForOrder(Long id);

    List<OrderProduct> findAll();
}
