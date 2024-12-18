package com.example.dscommerce_new.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserComRolesDTO {
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private List<RoleDTO> roles;
}
