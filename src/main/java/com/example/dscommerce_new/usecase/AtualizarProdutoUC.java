package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import com.example.dscommerce_new.schema.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarProdutoUC {
    private final ProductRepository productRepository;
    private final ConsultarProdutoPeloIdUC consultarProdutoPeloIdUC;
    private final ModelMapper modelMapper;

    public ProductDTO execute(Long id, ProductDTO productDTO) {
        consultarProdutoPeloIdUC.execute(id);
        consultarProdutoPeloIdUC.execute(productDTO.getId());
        productDTO.setId(id);
        Product product = productRepository.findById(id).get();
        product = productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }
}
