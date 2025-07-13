package com.asdok.orderservice.interfaces.rest.dto;

import com.asdok.orderservice.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        OrderStatus status,
        BigDecimal totalAmount,
        Instant createdAt) {
}
