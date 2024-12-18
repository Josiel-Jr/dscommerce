package com.example.dscommerce_new.controller.role;

import com.example.dscommerce_new.dto.RoleDTO;
import com.example.dscommerce_new.dto.RoleRequestDTO;
import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.dto.UserComRolesDTO;
import com.example.dscommerce_new.usecase.AtribuirRolesUC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/role")
public class AtribuirRolesController {
    private final AtribuirRolesUC atribuirRolesUC;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/atribuir/{id}")
    public ResponseEntity<UserAutenticadoDTO> atribuirRoles(@PathVariable Long id, @Valid @RequestBody List<RoleRequestDTO> roles){
        UserAutenticadoDTO userAutenticadoDTO = atribuirRolesUC.execute(id, roles);
        return ResponseEntity.ok(userAutenticadoDTO);
    }
}
