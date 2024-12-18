package com.example.dscommerce_new.controller.user;

import com.example.dscommerce_new.dto.UserDTO;
import com.example.dscommerce_new.usecase.CadastrarUserUC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class CadastrarUserController {
    private final CadastrarUserUC cadastrarUserUC;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> cadastrar(@Valid @RequestBody UserDTO userDTO) {
        UserDTO userDTOResult = cadastrarUserUC.execute(userDTO);
        return ResponseEntity.ok(userDTOResult);
    }
}
