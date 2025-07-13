package com.asdok.orderservice.interfaces.rest.dto;

import java.math.BigDecimal;

public record OrderRequest(BigDecimal totalAmount) {
}
