package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.custom.ProductSpecification;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import com.example.dscommerce_new.schema.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultarCatalogoUC {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> execute(ProductSpecification productSpecification){
        List<Product> products = productRepository.findAll(productSpecification);
        if(products.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum produto foi encontrado");
        }
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }
}
