package com.example.demo.constants;

import com.example.demo.entities.OrderState;

import java.util.Map;

public class Constants {
    public static final String ORDER_ALREADY_DELIVERED = "Order is Already delivered";
    public static final String ORDER_NOT_FOUND = "Order Not Found";
    public static final String ORDER ="order" ;

    public static final String ORDER_PATH ="/order" ;
    public static final String ADD ="/add" ;
    public static final String ADD_BY_EMAIL = "/addByEmail";
    public static final String OUT_FOR_DELIVERY = "/outForDelivery";
    public static final String DELIVERED = "/delivered";
    public static final String ORDERED ="/ordered" ;
    public static final String ALL="/all";
    public static final String ID_PATH_PARAM ="/{id}" ;
    public static final String JSON = "/json";
    public static final String PRODUCTS = "/products";
    public static final String PRODUCT_PATH = "/product";
    public static final String USER_PATH = "/user";

    public static Map<Integer, OrderState> states=null;
    public static final String ORDER_ALREADY_OUT_FORdELIVERY ="Order is Already out for delivery";
}
