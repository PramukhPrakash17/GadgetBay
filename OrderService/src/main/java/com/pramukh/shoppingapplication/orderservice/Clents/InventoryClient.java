package com.pramukh.shoppingapplication.orderservice.Clents;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory/checkinventory")
    boolean isInstock(@RequestParam String skucode, @RequestParam Integer quantity);


}