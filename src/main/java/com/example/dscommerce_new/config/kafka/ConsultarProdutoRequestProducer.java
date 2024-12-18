/*package com.example.dscommerce_new.config.kafka;

import com.example.dscommerce_new.dto.ProductDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultarProdutoRequestProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${kafka.topic.consultar-produto}")
    private String topicoConsultarProduto;

    public void publicarMensagemConsultarProduto(ProductDTO productDTO) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(productDTO);
        kafkaTemplate.send(topicoConsultarProduto, message);
    }
}
*/
