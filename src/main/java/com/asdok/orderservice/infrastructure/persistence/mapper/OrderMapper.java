package com.asdok.orderservice.infrastructure.persistence.mapper;

import com.asdok.orderservice.domain.model.Order;
import com.asdok.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toOrderEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
}
