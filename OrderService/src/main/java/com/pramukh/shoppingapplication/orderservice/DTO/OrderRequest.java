package com.pramukh.shoppingapplication.orderservice.DTO;

import java.math.BigDecimal;

public record OrderRequest(Long id,String skucode, BigDecimal price, Integer quantity,UserDetails userDetails) {
    public record UserDetails(String name, String email, String phone) {
    }

}
