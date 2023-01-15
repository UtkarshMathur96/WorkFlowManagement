package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_state")
public class OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id", nullable = false)
    private Integer id;
    @Column(name = "state")
    String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
