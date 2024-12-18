package com.example.dscommerce_new.projections;

import com.example.dscommerce_new.dto.*;
import com.example.dscommerce_new.schema.OrderStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderProjection {
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private PaymentDTO payment;
    private UserRequestUpdateDTO user;
    private List<OrderItemDTO> items;
}
