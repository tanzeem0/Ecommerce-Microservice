package com.springboot1to100.ecommerce.inventory_service.service;

import com.springboot1to100.ecommerce.inventory_service.dto.OrderRequestDto;
import com.springboot1to100.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.springboot1to100.ecommerce.inventory_service.dto.ProductDto;
import com.springboot1to100.ecommerce.inventory_service.entity.Product;
import com.springboot1to100.ecommerce.inventory_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public List<ProductDto> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> inventoryList = productRepository.findAll();
        return inventoryList.stream()
                .map(item -> modelMapper.map(item, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.map(item -> modelMapper.map(item, ProductDto.class))
                .orElseThrow(()->new RuntimeException("Inventory not found"));
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDto orderRequestItemDto:orderRequestDto.getItems())
        {
            Long productId = orderRequestItemDto.getProductId();
            Integer quantity = orderRequestItemDto.getQuantity();
            Product product = productRepository.findById(productId).orElseThrow(()->
                    new RuntimeException("Product not found with ID: " + productId));
            if(product.getStock() < quantity)
                throw new RuntimeException("Product cannot be fulfilled");
            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalPrice += product.getPrice()*quantity;
        }
        return totalPrice;
    }
}
