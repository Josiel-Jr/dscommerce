package com.example.dscommerce_new.controller.product;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.usecase.RemoverProdutoDoCarrinhoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produto")
public class RemoverProdutoDoCarrinhoPeloIdController {
    private final RemoverProdutoDoCarrinhoUC removerProdutoDoCarrinhoUC;

    @PreAuthorize("hasAnyAuthority('Admin','Cliente')")
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ProductDTO> removerProdutoDoCarrinhoPeloId(@PathVariable Long id) {
        ProductDTO productDTO = removerProdutoDoCarrinhoUC.execute(id);
        return ResponseEntity.ok(productDTO);
    }
}
