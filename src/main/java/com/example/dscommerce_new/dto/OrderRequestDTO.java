package com.example.dscommerce_new.dto;

import com.example.dscommerce_new.schema.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private Long id;
    @NotNull(message = "É necessário que o pedido possua um status")
    private OrderStatus status;
    @NotNull(message = "É necessário que o pedido possua um payment")
    private Long paymentId;
    @NotEmpty(message = "É necessário que o pedido possua itens")
    private List<OrderItemRequestDTO> items;
}
