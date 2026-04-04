package com.springboot1to100.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private String title;

    private Double price;

    private Integer stock;
}
