package com.example.demo.repository;

import com.example.demo.entities.OrderState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<OrderState, Integer> {
}
