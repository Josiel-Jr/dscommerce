package com.example.dscommerce_new.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "É necessário que o produto possua um nome")
    private String name;
    private String description;
    @NotNull(message = "É necessário que o produto possua um preço")
    @Positive(message = "É necessário que o preço do produto seja maior que zero")
    private Double price;
    @NotBlank(message = "É necessário que o produto possua uma imageUrl")
    private String imageUrl;
    @NotEmpty(message = "É necessário que o produto pertença a alguma categoria")
    private List<CategoryDTO> categories;
}
