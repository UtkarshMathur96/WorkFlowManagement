package com.example.demo.entities;


import javax.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    Order order;

    @Column(name = "quantity")
    Long quantity;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, Long quantity) {
        this.order=order;
        this.product=product;
        this.quantity=quantity;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
