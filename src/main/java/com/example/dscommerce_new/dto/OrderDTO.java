package com.example.dscommerce_new.dto;

import com.example.dscommerce_new.schema.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Instant moment;
    @NotBlank(message = "É necessário que o pedido possua um status")
    private OrderStatus status;
    private UserRequestUpdateDTO user;
    private PaymentDTO payment;
    @NotEmpty(message = "É necessário que o pedido possua itens")
    private List<OrderItemDTO> items;
}
