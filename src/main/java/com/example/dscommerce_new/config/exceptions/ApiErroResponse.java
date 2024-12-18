package com.example.dscommerce_new.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiErroResponse {
    private String message;
    private Integer code;
    private LocalDateTime timestamp;
}
