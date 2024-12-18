package com.example.dscommerce_new.controller.product;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.usecase.AtualizarProdutoUC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produto")
public class AtualizarProdutoController {

    private final AtualizarProdutoUC atualizarProdutoUC;

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProductDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO productDTOResult = atualizarProdutoUC.execute(id, productDTO);
        return ResponseEntity.ok(productDTOResult);
    }
}
