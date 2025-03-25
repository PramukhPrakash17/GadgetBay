package com.pramukh.shoppingapplication.orderservice.service;

import com.pramukh.shoppingapplication.orderservice.DTO.OrderRequest;
import com.pramukh.shoppingapplication.orderservice.DTO.OrderResponseDto;
import com.pramukh.shoppingapplication.orderservice.model.Order;
import com.pramukh.shoppingapplication.orderservice.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderResponseDto placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skucode(orderRequest.skucode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
        orderRepo.save(order);
        return new OrderResponseDto(order.getId(), order.getOrderNumber(), order.getSkucode(), order.getPrice(), order.getQuantity());

    }
}
