package com.asdok.orderservice.interfaces.rest;

import com.asdok.orderservice.application.OrderApplicationService;
import com.asdok.orderservice.domain.model.Order;
import com.asdok.orderservice.interfaces.rest.dto.OrderRequest;
import com.asdok.orderservice.interfaces.rest.dto.OrderResponse;
import com.asdok.orderservice.interfaces.rest.mapper.RestOrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderApplicationService orderService;
    private final RestOrderMapper mapper;

    public OrderController(OrderApplicationService orderService, RestOrderMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest request,
            @RequestHeader(name = "Idempotency-Key") UUID idempotencyKey
    ) {
        Optional<Order> existing = orderService.findByIdempotencyKey(idempotencyKey);
        Order order = existing.orElseGet(() -> {
            Order newOrder = mapper.toDomain(request);
            return orderService.createOrder(newOrder, idempotencyKey);
        });

        return new ResponseEntity<>(mapper.toResponse(order),
                existing.isPresent() ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<OrderResponse> payForOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toResponse(orderService.payForOrder(id)));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toResponse(orderService.cancelOrder(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toResponse(orderService.findById(id)));
    }
}
