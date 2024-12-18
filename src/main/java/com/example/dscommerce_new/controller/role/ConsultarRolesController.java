package com.example.dscommerce_new.controller.role;

import com.example.dscommerce_new.dto.UserComRolesDTO;
import com.example.dscommerce_new.usecase.ConsultarRolesUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/role")
public class ConsultarRolesController {
    private final ConsultarRolesUC consultarRolesUC;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<UserComRolesDTO> consultarRoles(@PathVariable Long id) {
        UserComRolesDTO userComRolesDTO = consultarRolesUC.execute(id);
        return ResponseEntity.ok(userComRolesDTO);
    }
}
