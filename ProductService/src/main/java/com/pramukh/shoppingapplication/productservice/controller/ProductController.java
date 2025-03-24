package com.pramukh.shoppingapplication.productservice.controller;

import com.pramukh.shoppingapplication.productservice.DTO.ProductRequestDto;
import com.pramukh.shoppingapplication.productservice.DTO.ProductResponseDto;
import com.pramukh.shoppingapplication.productservice.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> product(@RequestBody ProductRequestDto productRequestDto) {

        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.ACCEPTED);
    }

}
