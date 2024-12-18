package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverProdutoDoCarrinhoUC {
    private final ProductRepository productRepository;
    private final ConsultarProdutoPeloIdUC consultarProdutoPeloId;

    @Transactional
    public ProductDTO execute(Long id) {
        ProductDTO productDTO = consultarProdutoPeloId.execute(id);
        productRepository.deleteProductById(id);
        return productDTO;
    }

}
