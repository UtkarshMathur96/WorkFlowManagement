package com.example.demo.controllers;

import com.example.demo.DTO.DTO;
import com.example.demo.JSONUtil;
import com.example.demo.constants.Constants;
import com.example.demo.controllers.services.OrderService;
import com.example.demo.controllers.services.ProductService;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping(path = Constants.ORDER_PATH)
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @PostMapping(path = Constants.ADD)
    public @ResponseBody
    String addOrder(@RequestParam Long userID, @RequestParam String products) {

        return orderService.addOrder(userID, products);
    }

    @PostMapping(path = Constants.ADD_BY_EMAIL)
    public @ResponseBody
    String addOrderByEmail(@RequestBody String body) {
        DTO message = orderService.addOrderByEmail(body);
        return JSONUtil.OkResponse(message);
    }

    @PostMapping(path = Constants.ORDERED)
    public @ResponseBody
    String ordered(@RequestParam Long orderID) {
        return orderService.ordered(orderID);

    }

    @PostMapping(path = Constants.OUT_FOR_DELIVERY)
    public @ResponseBody
    String outForDelivery(@RequestParam Long orderID) {
        return orderService.outForDelivery(orderID);

    }

    @PostMapping(path = Constants.DELIVERED)
    public @ResponseBody
    String delivered(@RequestParam Long orderID) {
        return orderService.delivered(orderID);
    }

    @GetMapping(path = Constants.ALL)
    public @ResponseBody
    Iterable<Order> getAllOrder() {
        // This returns a JSON or XML with the users
        return orderService.findAll();
    }

    @GetMapping(path = Constants.ID_PATH_PARAM + Constants.JSON)
    public @ResponseBody
    Optional<Order> getOrderDetail(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping(path = Constants.ID_PATH_PARAM)
    public String getOrderPage(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.findById(id);
        model.addAttribute(Constants.ORDER, order.get());
        return "orderDetail";
    }

    @GetMapping(path = Constants.ID_PATH_PARAM +Constants.PRODUCTS)
    public @ResponseBody
    Collection<OrderProduct> getOrderProducts(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        Collection<OrderProduct> products = null;
        if (order.isPresent()) {
            products = productService.getProductsForOrder(order.get().getId());

        }
        return products;
    }
}
