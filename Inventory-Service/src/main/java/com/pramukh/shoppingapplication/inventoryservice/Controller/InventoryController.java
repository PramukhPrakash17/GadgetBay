package com.pramukh.shoppingapplication.inventoryservice.Controller;

import com.pramukh.shoppingapplication.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/checkinventory")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkAvailable(@RequestParam String skucode, @RequestParam Integer quantity) {
        return inventoryService.checkStock(skucode,quantity);
    }
}
