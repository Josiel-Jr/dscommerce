package com.example.dscommerce_new.controller.order;

import com.example.dscommerce_new.dto.OrderDTO;
import com.example.dscommerce_new.dto.OrderRequestDTO;
import com.example.dscommerce_new.usecase.AdicionarOrderInPaymentUC;
import com.example.dscommerce_new.usecase.RegistrarPedidoUC;
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
@RequestMapping("/pedido")
public class RegistrarPedidoController {

    private final RegistrarPedidoUC registrarPedidoUC;
    private final AdicionarOrderInPaymentUC adicionarOrderInPaymentUC;

    @PreAuthorize("hasAnyAuthority('Admin', 'Cliente')")
    @PostMapping("/registrar")
    public ResponseEntity<OrderDTO> registrarPedido(@Valid @RequestBody OrderRequestDTO orderDTO) {
        OrderDTO result = registrarPedidoUC.execute(orderDTO);
        adicionarOrderInPaymentUC.execute(result.getPayment().getId() ,result.getId());
        return ResponseEntity.ok(result);
    }
}
