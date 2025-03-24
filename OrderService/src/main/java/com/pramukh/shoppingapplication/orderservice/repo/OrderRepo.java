package com.pramukh.shoppingapplication.orderservice.repo;

import com.pramukh.shoppingapplication.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
