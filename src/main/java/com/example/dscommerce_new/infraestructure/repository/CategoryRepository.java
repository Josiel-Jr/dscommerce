package com.example.dscommerce_new.infraestructure.repository;

import com.example.dscommerce_new.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
