package com.example.dscommerce_new.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "É necessário que a categoria possua um nome")
    private String name;
    //private List<ProductDTO> products;
}
