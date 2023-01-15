package com.example.demo.controllers.services;

import com.example.demo.DTO.DTO;
import com.example.demo.entities.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);

    Iterable<Order> findAll();

    String addOrder(Long userID, String products);

    DTO addOrderByEmail(String email);

    String outForDelivery(Long orderID);

    String delivered(Long orderID);

    String ordered(Long orderID);
}
