package com.example.dscommerce_new.controller.product;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.usecase.ConsultarProdutoPeloIdUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ConsultarProdutoPeloIdController {
    private final ConsultarProdutoPeloIdUC consultarProdutoPeloIdUC;

    @PreAuthorize("hasAnyAuthority('Admin','Cliente')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> consultarProdutoPeloId(@PathVariable Long id) {
        ProductDTO productDTO = consultarProdutoPeloIdUC.execute(id);
        return ResponseEntity.ok(productDTO);
    }
}
