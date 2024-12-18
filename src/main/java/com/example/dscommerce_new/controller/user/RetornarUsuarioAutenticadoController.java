package com.example.dscommerce_new.controller.user;

import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.dto.UserComRolesDTO;
import com.example.dscommerce_new.dto.UserDTO;
import com.example.dscommerce_new.usecase.RetornarUsuarioAutenticadoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class RetornarUsuarioAutenticadoController {
    private final RetornarUsuarioAutenticadoUC retornarUsuarioAutenticadoUC;

    @PreAuthorize("hasAnyAuthority('Admin','Cliente')")
    @GetMapping("/autenticado")
    public ResponseEntity<UserAutenticadoDTO> retornarUsuarioAutenticado() {
        UserAutenticadoDTO userAutenticadoDTO = retornarUsuarioAutenticadoUC.execute();
        return ResponseEntity.ok(userAutenticadoDTO);
    }
}
