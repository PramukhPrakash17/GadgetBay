package com.pramukh.shoppingapplication.orderservice.DTO;

import java.math.BigDecimal;

public record OrderResponseDto(Long id, String orderNumber, String skucode, BigDecimal price, Integer quantity) {
}
