package com.example.dscommerce_new.controller.categoria;

import com.example.dscommerce_new.dto.CategoryDTO;
import com.example.dscommerce_new.usecase.AtualizarCategoriaUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class AtualizarCategoriaController {
    private final AtualizarCategoriaUC atualizarCategoriaUC;

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CategoryDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTOResult = atualizarCategoriaUC.execute(id, categoryDTO);
        return ResponseEntity.ok(categoryDTOResult);
    }
}
