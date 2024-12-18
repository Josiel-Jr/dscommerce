package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.IdAtribuidaComEntidade;
import com.example.dscommerce_new.dto.PaymentDTO;
import com.example.dscommerce_new.infraestructure.repository.PaymentRepository;
import com.example.dscommerce_new.schema.Payment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RegistrarPaymentUC {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PaymentDTO execute(Long paymentId){
        paymentRepository.findById(paymentId).ifPresent(payment -> {
            throw new IdAtribuidaComEntidade("Já possui payment cadastrado com o id: %s".formatted(paymentId));
        });
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setMoment(Instant.now());
        paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }
}
