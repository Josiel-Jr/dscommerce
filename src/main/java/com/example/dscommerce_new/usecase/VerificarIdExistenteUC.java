package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.IdAtribuidaComEntidade;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificarIdExistenteUC{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public void execute(Long id) {
        productRepository.findById(id).
                ifPresent(product -> {
                    throw new IdAtribuidaComEntidade("O id: %s. Encontra-se vinculado a outro produto".formatted(product.getId()));
                });
    }
}
