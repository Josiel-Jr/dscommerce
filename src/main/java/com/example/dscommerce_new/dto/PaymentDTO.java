package com.example.dscommerce_new.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PaymentDTO {
    private Long id;
    private Instant moment;
}
