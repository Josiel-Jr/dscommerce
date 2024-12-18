package com.example.dscommerce_new.controller.product;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.usecase.AdicionarProdutoAoCarrinhoUC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/produto")
@RequiredArgsConstructor
public class AdicionarProdutoAoCarrinhoController {

    private final AdicionarProdutoAoCarrinhoUC adicionarProdutoAoCarrinhoUC;

    @PreAuthorize("hasAnyAuthority('Admin','Cliente')")
    @PostMapping("/adicionar-carrinho")
    public ResponseEntity<ProductDTO> adicionarProdutoAoCarrinho(@Valid @RequestBody ProductDTO productDTO) {
        productDTO = adicionarProdutoAoCarrinhoUC.execute(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }
}
