package com.asdok.orderservice.application;

import com.asdok.orderservice.domain.model.Order;
import com.asdok.orderservice.domain.model.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order payForOrder(UUID orderId) {
        Order order = getOrder(orderId);
        order.markAsPaid();
        return orderRepository.update(order);
    }

    public Order cancelOrder(UUID id) {
        Order order = getOrder(id);
        order.cancel();
        return orderRepository.update(order);
    }

    public Order createOrder(Order newOrder, UUID idempotencyKey) {
        return orderRepository.create(newOrder, idempotencyKey);
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public Optional<Order> findByIdempotencyKey(UUID idempotencyKey) {
        return orderRepository.findByIdempotencyKey(idempotencyKey);
    }

    private Order getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
