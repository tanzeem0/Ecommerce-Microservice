package com.springboot1to100.ecommerce.inventory_service.controller;

import com.netflix.appinfo.InstanceInfo;
import com.springboot1to100.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.springboot1to100.ecommerce.inventory_service.dto.OrderRequestDto;
import com.springboot1to100.ecommerce.inventory_service.dto.ProductDto;
import com.springboot1to100.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public String fetchOrderFromOrderService(){
//        ServiceInstance instanceInfo = discoveryClient.getInstances("order-service").getFirst();
//        log.info("Complete URI:{}",instanceInfo.getUri() + "/api/v1/orders/helloOrders");
//        return restClient.get().uri(instanceInfo.getUri() + "/api/v1/orders/helloOrders").retrieve().body(String.class);
        return ordersFeignClient.helloOrders();
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

    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }
}
