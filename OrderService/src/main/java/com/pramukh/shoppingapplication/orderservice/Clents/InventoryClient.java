package com.pramukh.shoppingapplication.orderservice.Clents;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory-service" , url = "${inventory-url}")
public interface InventoryClient {

    @GetMapping("/api/inventory/checkinventory")
    boolean isInstock(@RequestParam String skucode, @RequestParam Integer quantity);


}