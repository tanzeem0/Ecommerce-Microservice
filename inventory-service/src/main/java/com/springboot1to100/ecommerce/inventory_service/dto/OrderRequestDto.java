package com.springboot1to100.ecommerce.inventory_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> items;
    private Double totalPrice;
}
