package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import com.example.dscommerce_new.schema.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarProdutoPeloIdUC {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDTO execute(Long id){
        Product product = productRepository.findById(id).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi encontrado um produto com o id: %s".formatted(id)));
        return modelMapper.map(product, ProductDTO.class);
    }
}
