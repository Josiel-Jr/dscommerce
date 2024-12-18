package com.example.dscommerce_new.controller.user;

import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.dto.UserRequestUpdateDTO;
import com.example.dscommerce_new.usecase.AtualizarUserUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class AtualizarUserController {
    private final AtualizarUserUC atualizarUserUC;

    @PreAuthorize("hasAnyAuthority('Admin','Cliente')")
    @PutMapping("/atualizar")
    public ResponseEntity<UserAutenticadoDTO> atualizarUser(@RequestBody UserRequestUpdateDTO userDTO) {
        UserAutenticadoDTO userResultDTO = atualizarUserUC.execute(userDTO);
        return ResponseEntity.ok(userResultDTO);
    }
}
