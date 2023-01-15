package com.example.demo.controllers.services;

import com.example.demo.DTO.ProductsDTO;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProduct;
import com.example.demo.entities.Product;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository opRepository;
    @Override
    @Async
    public void addProductsToOrder(Order order, ArrayList<ProductsDTO> products) {
        int count=0;
        for (ProductsDTO productDTO : products) {
            Optional<Product> pro = productRepository.findByName(productDTO.getName());
            if(pro.isPresent()){
                //add to order
                OrderProduct op= new OrderProduct(order,pro.get(),Long.parseLong(productDTO.getQuantity()));
                opRepository.save(op);
                count++;
            }else{
                logger.error(String.format("Order with name %s not Found",productDTO.getName()));
            }

        }
        logger.info (String.format("Added %d products to Order",count));

    }

    @Override
    public Collection<OrderProduct> getProductsForOrder(Long id) {
        Collection<OrderProduct> product = opRepository.findByOrderId(id);
        return product;
    }

    @Override
    public List<OrderProduct> findAll() {
        return opRepository.findAll();
    }
}
