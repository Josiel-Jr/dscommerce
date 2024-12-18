package com.example.dscommerce_new.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderItemDTO {
    private OrderItemPKDTO id;

    @Min(value = 1, message = "É necessário que o produto possua uma quantidade maior que zero")
    private Integer quantity;
    private Double price;

    public Double getPrice() {
        return id.getProduct().getPrice()*quantity;
    }
}
