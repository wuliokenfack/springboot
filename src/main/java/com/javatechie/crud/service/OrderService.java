package com.javatechie.crud.service;

import com.javatechie.crud.entity.Order;
import com.javatechie.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrderById(long id) {
        Order order = repository.getOne(id);
        if (order == null) {
            throw new IllegalArgumentException("Invalid id : " + id);
        } else {
            return order;
        }
    }
}
