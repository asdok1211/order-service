package com.asdok.orderservice.infrastructure.persistence.adapter;

import com.asdok.orderservice.domain.model.Order;
import com.asdok.orderservice.domain.model.repository.OrderRepository;
import com.asdok.orderservice.infrastructure.persistence.entity.OrderEntity;
import com.asdok.orderservice.infrastructure.persistence.mapper.OrderMapper;
import com.asdok.orderservice.infrastructure.persistence.repository.JpaOrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    private final OrderMapper orderMapper;

    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository, OrderMapper orderMapper) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Optional<Order> findByIdempotencyKey(UUID key) {
        return jpaOrderRepository.findByIdempotencyKey(key).map(orderMapper::toOrder);
    }

    @Override
    public Order update(Order order) {
        return orderMapper.toOrder(
                jpaOrderRepository.save(orderMapper.toOrderEntity(order))
        );
    }

    @Override
    public Order create(Order order, UUID idempotencyKey) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        orderEntity.setIdempotencyKey(idempotencyKey);
        return orderMapper.toOrder(
                jpaOrderRepository.save(orderEntity)
        );
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return jpaOrderRepository.findById(orderId).map(orderMapper::toOrder);
    }
}
