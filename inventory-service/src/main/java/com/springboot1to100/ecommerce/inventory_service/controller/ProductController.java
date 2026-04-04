package com.springboot1to100.ecommerce.inventory_service.controller;

import com.netflix.appinfo.InstanceInfo;
import com.springboot1to100.ecommerce.inventory_service.dto.ProductDto;
import com.springboot1to100.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;

    @GetMapping("/fetchOrders")
    public String fetchOrderFromOrderService(){
        ServiceInstance instanceInfo = discoveryClient.getInstances("order-service").getFirst();
        log.info("Complete URI:{}",instanceInfo.getUri() + "/api/v1/orders/helloOrders");
        return restClient.get().uri(instanceInfo.getUri() + "/api/v1/orders/helloOrders").retrieve().body(String.class);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }
}
