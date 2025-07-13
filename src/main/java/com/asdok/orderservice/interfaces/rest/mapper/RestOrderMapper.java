package com.asdok.orderservice.interfaces.rest.mapper;

import com.asdok.orderservice.domain.model.Order;
import com.asdok.orderservice.interfaces.rest.dto.OrderRequest;
import com.asdok.orderservice.interfaces.rest.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestOrderMapper {

    @Mapping(target = "status", expression = "java(com.asdok.orderservice.domain.model.OrderStatus.PENDING)")
    Order toDomain(OrderRequest request);

    OrderResponse toResponse(Order order);
}
