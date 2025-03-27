package com.pramukh.shoppingapplication.inventoryservice.Service;

import com.pramukh.shoppingapplication.inventoryservice.Repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Boolean checkStock(String skucode, Integer quantity) {
         return  inventoryRepository.existsBySkucodeAndQuantityIsGreaterThanEqual(skucode, quantity);
    }
}
