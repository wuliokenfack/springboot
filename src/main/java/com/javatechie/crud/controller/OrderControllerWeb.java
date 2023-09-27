package com.javatechie.crud.controller;

import com.javatechie.crud.entity.Order;
import com.javatechie.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web-orders/")
public class OrderControllerWeb {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("")
    public String showHome(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "index";
    }

    @GetMapping("add-orders")
    public String addOrderForm(Order order) {
        return "add-orders";
    }

    @PostMapping("add")
    public String createNew(@javax.validation.Valid Order newOrder, BindingResult result, Model model) {
        System.out.println("I am here");
        if (result.hasErrors()) {
            System.out.println("I am here 2");
            return "index";
        }
        orderRepository.save(newOrder);
        return "redirect:list";
    }

    @GetMapping("list")
    public String showListForm(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "index";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.getOne(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        if (order == null) {
            throw new IllegalArgumentException("Invalid id : " + id);
        }
        model.addAttribute("order", order);
        return "update-order";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.getOne(id);

        if (order == null) {
            throw new IllegalArgumentException("Invalid id : " + id);
        }

//                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        orderRepository.delete(order);
        model.addAttribute("orders", orderRepository.findAll());
        return "index";
    }

    @PostMapping("update/{id}")
    public String updateOrder(@PathVariable("id") long id, @javax.validation.Valid Order order, BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            order.setId(id);
            return "update-order";
        }

        orderRepository.save(order);
        model.addAttribute("orders", orderRepository.findAll());
        return "index";
    }

}
