package com.example.dscommerce_new.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrors {

    private Integer code;

    private LocalDateTime timestamp;

    private List<String> errors;
}
