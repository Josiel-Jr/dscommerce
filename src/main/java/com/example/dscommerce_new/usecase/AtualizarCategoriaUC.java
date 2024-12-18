package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.dto.CategoryDTO;
import com.example.dscommerce_new.infraestructure.repository.CategoryRepository;
import com.example.dscommerce_new.schema.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarCategoriaUC {
    private final CategoryRepository categoryRepository;
    private final ConsultarCategoriaPeloIdUC consultarCategoriaPeloId;
    private final ModelMapper modelMapper;

    public CategoryDTO execute(Long id, CategoryDTO categoryDTO) {
        consultarCategoriaPeloId.execute(id);
        Category category = categoryRepository.findById(id).get();
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }
}
