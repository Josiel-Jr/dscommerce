package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.CategoryDTO;
import com.example.dscommerce_new.infraestructure.repository.CategoryRepository;
import com.example.dscommerce_new.schema.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarCategoriaPeloIdUC {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryDTO execute(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui categoria com o id: %s".formatted(id)));
        return modelMapper.map(category, CategoryDTO.class);
    }
}
