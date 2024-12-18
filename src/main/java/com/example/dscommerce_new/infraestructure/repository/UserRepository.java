package com.example.dscommerce_new.infraestructure.repository;

import com.example.dscommerce_new.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
