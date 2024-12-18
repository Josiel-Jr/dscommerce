package com.example.dscommerce_new.controller.order;

import com.example.dscommerce_new.dto.OrderDTO;
import com.example.dscommerce_new.projections.OrderProjection;
import com.example.dscommerce_new.usecase.VisualizarPedidosProjectionUC;
import com.example.dscommerce_new.usecase.VisualizarPedidosUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class VisualizarPedidosController {
    private final VisualizarPedidosUC visualizarPedidosUC;

    private final VisualizarPedidosProjectionUC visualizarPedidosProjectionUC;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/visualizar")
    public ResponseEntity<List<OrderDTO>> visualizarPedidos() {
        List<OrderDTO> orderDTOs = visualizarPedidosUC.execute();
        return ResponseEntity.ok(orderDTOs);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/visualizar-projection")
    public ResponseEntity<List<OrderProjection>> visualizarPedidosProjection() {
        List<OrderProjection> orderProjections = visualizarPedidosProjectionUC.execute();
        return ResponseEntity.ok(orderProjections);
    }
}
