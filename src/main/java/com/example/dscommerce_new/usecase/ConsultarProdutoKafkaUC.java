/*package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.kafka.ConsultarProdutoRequestProducer;
import com.example.dscommerce_new.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class ConsultarProdutoKafkaUC {
    private final ConsultarProdutoPeloIdUC consultarProdutoPeloIdUC;

    private final ConsultarProdutoRequestProducer producer;

    public void execute(Long id){
        ProductDTO productDTO = consultarProdutoPeloIdUC.execute(id);
        log.info("Publicando nova mensagem no kafka: %s".formatted(productDTO.toString()));
        try {
            producer.publicarMensagemConsultarProduto(productDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
*/