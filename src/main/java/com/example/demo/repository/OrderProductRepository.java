package com.example.demo.repository;

import com.example.demo.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query(value = "select * from order_product od where order_id  = ?1",nativeQuery = true)
    Collection<OrderProduct> findByOrderId(Long id);
}
