package com.example.dscommerce_new.infraestructure.repository;

import com.example.dscommerce_new.schema.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = """
            update tb_payment 
            set order_id = :orderId
            where id = :paymentId
""")
    void adicionarOrderInPayment(Long paymentId, Long orderId);
}
