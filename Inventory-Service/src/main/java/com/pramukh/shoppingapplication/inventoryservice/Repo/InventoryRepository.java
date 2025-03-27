package com.pramukh.shoppingapplication.inventoryservice.Repo;

import com.pramukh.shoppingapplication.inventoryservice.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    boolean existsBySkucodeAndQuantityIsGreaterThanEqual(String skucode, Integer quantity);
}
