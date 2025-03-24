package com.pramukh.shoppingapplication.productservice.repo;


import com.pramukh.shoppingapplication.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends MongoRepository<Product, String> {
}
