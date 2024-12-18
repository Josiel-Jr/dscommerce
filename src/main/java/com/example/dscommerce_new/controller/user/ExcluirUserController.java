package com.example.dscommerce_new.controller.user;

import com.example.dscommerce_new.dto.UserDTO;
import com.example.dscommerce_new.usecase.ExcluirUserUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class ExcluirUserController {
    private final ExcluirUserUC excluirUserUC;

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<UserDTO> excluirUser(@PathVariable Long id) {
        UserDTO userDTO = excluirUserUC.execute(id);
        return ResponseEntity.ok(userDTO);
    }
}
