package com.example.demo.controllers.services;

import com.example.demo.DTO.DTO;
import com.example.demo.DTO.ErrorDTO;
import com.example.demo.DTO.IdDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.constants.Constants;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderState;
import com.example.demo.entities.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Optional;

import static com.example.demo.constants.Constants.states;

@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public String addOrder(Long userID, String products) {

        Optional<User> user = userRepository.findById((userID));
        if (user.isPresent()) {
            Order order = new Order();
            // By default orders should be in created state
            order.setState(states.get(1));
            order.setUser(user.get());
            orderRepository.save(order);
            return order.toString();
        } else {
            return "User Not Found";
        }
    }

    public DTO addOrderByEmail(String body) {

        System.out.println("body = " + body);
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO dto = null;
        try {
            dto = objectMapper.readValue(body, OrderDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("dto = " + dto);
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
//        Optional<User> user = userRepository.findById((userID));
        if (user.isPresent()) {
            Order order = new Order();
            // By default orders should be in created state
            order.setState(states.get(1));
            order.setUser(user.get());
            orderRepository.save(order);
            logger.info("before Async called ");
            // This is an async call which would add all the products to the newly created order.
            // The user will still be able to open the order page, while this happens in the background
            productService.addProductsToOrder(order, dto.getProducts());
            logger.info("after Async called ");
            return new IdDTO(order.getId().toString());
        } else {
            return new ErrorDTO(Constants.ORDER_NOT_FOUND);
        }
    }

    public String outForDelivery(Long orderID) {
        Optional<Order> order = orderRepository.findById(orderID);
        if (order.isPresent()) {
            Order val = order.get();
            if (val.getState().getState().equals(states.get(1).getState())) {  // if state is ORDERED, only then it can be changed to OUT_FOR_DELIVERY
                val.setState(states.get(2));
                orderRepository.save(val);
                return orderRepository.findById(orderID).toString();
            } else if (val.getState().getState().equals(states.get(2).getState())) {
                return Constants.ORDER_ALREADY_OUT_FORdELIVERY;
            } else if (val.getState().getState().equals(states.get(3).getState())) {
                return Constants.ORDER_ALREADY_DELIVERED;
            }
        }
        return Constants.ORDER_NOT_FOUND;
    }


    public String delivered(Long orderID) {
        Optional<Order> order = orderRepository.findById(orderID);
        if (order.isPresent()) {
            Order val = order.get();
            if (val.getState().getState().equals(states.get(2).getState())) {  // if state is OUT_FOR_DELIVERY, only then it can be changed to DELIVERED
                val.setState(states.get(3));
                orderRepository.save(val);
            } else {
                return "Order Not Out For Delivery";
            }
            return orderRepository.findById(orderID).toString();

        }
        return "Order Not Found";
    }

    @Override
    public String ordered(Long orderID) {
        Optional<Order> order = orderRepository.findById(orderID);
        if (order.isPresent()) {
            Order val = order.get();
            // if state is either OUT_FOR_DELIVERY or DELIVERED, then it can be changed to ORDERED
            // this is added just as a rollback mechanism
            if (val.getState().getState().equals(states.get(2).getState())
                    ||
                    val.getState().getState().equals(states.get(3).getState())) {
                val.setState(states.get(1));
                orderRepository.save(val);
            } else {
                return "Order Not Out For Delivery or DELIVERED";
            }
            return orderRepository.findById(orderID).toString();

        }
        return "Order Not Found";
    }


    @PostConstruct
    public void init() {
        loadStates();
    }

    public void loadStates() {
        // This will load in the static state info when the service loads
        if (states == null) {
            states = new HashMap<>();
            Iterable<OrderState> list = stateRepository.findAll();
            for (OrderState orderState : list) {
                states.put(orderState.getId(), orderState);
            }
        }
    }
}
