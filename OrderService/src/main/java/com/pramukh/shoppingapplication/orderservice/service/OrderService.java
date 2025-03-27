package com.pramukh.shoppingapplication.orderservice.service;

import com.pramukh.shoppingapplication.orderservice.Clents.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public String placeOrder(OrderRequest orderRequest) {
        if(!inventoryClient.isInstock(orderRequest.skucode(), orderRequest.quantity())) {
            throw new RuntimeException("Product with name " + orderRequest.skucode() + "out of stock");
        }
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skucode(orderRequest.skucode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
        orderRepo.save(order);
        return "Order Placed Successfully";
    }
}
