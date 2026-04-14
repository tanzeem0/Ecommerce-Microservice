package com.springboot1to100.ecommerce.order_service.controller;

import com.springboot1to100.ecommerce.order_service.dto.OrderRequestDto;
import com.springboot1to100.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class OrdersController {

    private final OrdersService ordersService;
    @Value("${my.variable}")
    private  String myVariable;

    @GetMapping("/helloOrders")
    public String helloOrder(@RequestHeader("X-User-Id") Long userId){
        return "Hello from Order Service with user: " + userId + " myvariable: " + myVariable;
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1 = ordersService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }

    @GetMapping()
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest){
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        log.info("Fetching order with ID: {} via controller" , id);
        OrderRequestDto order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
