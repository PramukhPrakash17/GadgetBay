package com.pramukh.shoppingapplication.orderservice.service;

import com.pramukh.shoppingapplication.orderservice.Clents.InventoryClient;
import com.pramukh.shoppingapplication.orderservice.DTO.OrderRequest;
import com.pramukh.shoppingapplication.orderservice.DTO.OrderResponseDto;

import com.pramukh.shoppingapplication.orderservice.event.OrderPlacedEvent;
import com.pramukh.shoppingapplication.orderservice.model.Order;
import com.pramukh.shoppingapplication.orderservice.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

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

        OrderPlacedEvent orderPLaceEvent = new OrderPlacedEvent();
        orderPLaceEvent.setOrderNumber(order.getOrderNumber());
        orderPLaceEvent.setEmail(orderRequest.userDetails().email());
        orderPLaceEvent.setName(orderRequest.userDetails().name());
        kafkaTemplate.send("order-placed", orderPLaceEvent);
        log.info("Order Placed Event sent to Kafka");


        return "Order Placed Successfully";
    }
}
