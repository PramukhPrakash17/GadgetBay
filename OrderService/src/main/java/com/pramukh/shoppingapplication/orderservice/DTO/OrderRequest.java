package com.pramukh.shoppingapplication.orderservice.DTO;

import java.math.BigDecimal;

public record OrderRequest(String skucode, BigDecimal price, Integer quantity) {
}
