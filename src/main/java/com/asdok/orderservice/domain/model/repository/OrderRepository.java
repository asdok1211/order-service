package com.asdok.orderservice.domain.model.repository;

import com.asdok.orderservice.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findByIdempotencyKey(UUID key);
    Order update(Order order);
    Order create(Order order, UUID idempotencyKey);
    Optional<Order> findById(UUID orderId);
}
