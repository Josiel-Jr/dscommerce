package com.example.dscommerce_new.infraestructure.repository;

import com.example.dscommerce_new.dto.ProductDTO;
import com.example.dscommerce_new.schema.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Override
    List<Product> findAll(Specification<Product> specification);

    void deleteProductById(Long id);
    /*
    @Query(nativeQuery = true, value = """
            delete tb_order_item, tb_product_category, tb_product
            from tb_order_item
            inner join tb_product_category on tb_order_item.product_id=tb_product_category.product_id
            inner join tb_product on tb_product_category.product_id=tb_product.id
            where tb_product.id = :id
""")
    Product deleteProductById(Long id);*/

}
