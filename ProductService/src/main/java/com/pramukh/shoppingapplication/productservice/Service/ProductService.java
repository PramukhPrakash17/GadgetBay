package com.pramukh.shoppingapplication.productservice.Service;

import com.pramukh.shoppingapplication.productservice.DTO.ProductRequestDto;
import com.pramukh.shoppingapplication.productservice.DTO.ProductResponseDto;
import com.pramukh.shoppingapplication.productservice.model.Product;
import com.pramukh.shoppingapplication.productservice.repo.productRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Builder
public class ProductService {
    private final productRepo productRepo;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.productName())
                .description(productRequestDto.productDescription())
                .price(productRequestDto.productPrice())
                .build();
        productRepo.save(product);

        log.info("Product created successfully");
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());

    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products= productRepo.findAll();
        return products.stream().map(product -> new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }

    public void  deleteProduct(String id) {
         productRepo.deleteById(id);
         log.info("Product deleted successfully");


    }
}
