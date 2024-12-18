package com.example.dscommerce_new.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotNull(message = "É necessário fornecer o id do role que será atribuído ao user.")
    private Long id;
}
