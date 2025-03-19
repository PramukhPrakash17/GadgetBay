package com.pramukh.shoppingapplication.productservice.DTO;

import java.math.BigDecimal;

public record ProductRequestDto(String productName, String productDescription, BigDecimal productPrice) {

}
