package com.gkcocoa.controller;

import jakarta.validation.Valid;
import com.gkcocoa.model.Order;
import com.gkcocoa.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public Order getOrder(
            @PathVariable Long id,
            @RequestParam String phone) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getPhone().equals(phone)) {
            throw new RuntimeException("Phone number does not match");
        }

        return order;
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}