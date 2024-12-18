package com.example.dscommerce_new.controller.product;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.infraestructure.custom.ProductSpecification;
import com.example.dscommerce_new.usecase.ConsultarCatalogoUC;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ConsultarCatalogoController {
    private final ConsultarCatalogoUC consultarCatalogoUC;

    @GetMapping("/consultar")
    public ResponseEntity<List<ProductDTO>> consultarCatalogo(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String description,
                                                              @RequestParam(required = false) @Positive(message = "É necessário que o preço do produto seja maior que zero") Double price,
                                                              @RequestParam(required = false) String nameCategory) {

        ProductSpecification productSpecification = new ProductSpecification(name, description, price, nameCategory);
        List<ProductDTO> productDTOs = consultarCatalogoUC.execute(productSpecification);
        return ResponseEntity.ok(productDTOs);
    }
}
