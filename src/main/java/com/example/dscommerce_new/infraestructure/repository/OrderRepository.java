package com.example.dscommerce_new.infraestructure.repository;

import com.example.dscommerce_new.schema.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = """
            select * from tb_order
            where payment_id = :paymentId
""")
    Optional<Order> paymentInOrder(Long paymentId);

}
