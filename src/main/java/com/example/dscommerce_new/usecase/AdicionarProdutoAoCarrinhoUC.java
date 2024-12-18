package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.repository.CategoryRepository;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import com.example.dscommerce_new.schema.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarProdutoAoCarrinhoUC {
    private final ProductRepository productRepository;
    private final ConsultarProdutoPeloIdUC consultarProdutoPeloIdUC;
    private final VerificarIdExistenteUC verificarIdExistenteUC;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductDTO execute(ProductDTO productDTO) {
        ProductDTO productDTOResult = consultarProduto(productDTO.getId());
        verificarIdExistenteUC.execute(productDTO.getId());
        productDTO.getCategories().forEach(categoryDTO -> {
            categoryRepository.findById(categoryDTO.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui categoria com o id: %s ".formatted(categoryDTO.getId())));
        });
        Product product = productRepository.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(product, ProductDTO.class);
    }

    private ProductDTO consultarProduto(Long id){
        try{
            return id != null ? consultarProdutoPeloIdUC.execute(id) : null;
        }
        catch(EntidadeNaoEncontradaException e){
            return null;
        }
    }

}
