package com.asdok.orderservice.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {

    private final UUID id;
    private OrderStatus status;
    private final BigDecimal totalAmount;

    public Order(UUID id, BigDecimal totalAmount) {
        this.id = id;
        this.status = OrderStatus.PENDING;
        this.totalAmount = totalAmount;
    }

    public void markAsPaid() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be paid.");
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel a shipped or already cancelled order.");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
