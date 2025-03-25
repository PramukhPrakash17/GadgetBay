package com.pramukh.shoppingapplication.inventoryservice.Controller;

import com.pramukh.shoppingapplication.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/checkinventory")
    public ResponseEntity<Boolean> getInventory(@RequestParam String skucode, @RequestParam Integer quantity) {
        return new ResponseEntity<>(inventoryService.checkStock(skucode,quantity), HttpStatus.OK);
    }
}
