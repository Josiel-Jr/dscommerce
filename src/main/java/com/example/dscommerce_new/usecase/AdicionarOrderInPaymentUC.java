package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.infraestructure.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarOrderInPaymentUC {
    private final PaymentRepository paymentRepository;

    public void execute(Long paymentId, Long orderId){
        try{
            paymentRepository.adicionarOrderInPayment(paymentId, orderId);
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}
