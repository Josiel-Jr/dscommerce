package com.example.dscommerce_new.infraestructure.custom;

import com.example.dscommerce_new.schema.Category;
import com.example.dscommerce_new.schema.Product;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification implements Specification<Product> {

    public String name;
    public String description;
    public Double price;
    public String nameCategory;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(this.name != null && !this.name.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name + "%"));
        }

        if(this.description != null && !this.description.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("description"), this.description));
        }

        if(this.price != null && this.price>0) {
            if ((this.price - this.price.intValue()) != 0) { //verifica se possui número depois da vírgula
                predicates.add(criteriaBuilder.like(root.get("price").as(String.class), this.price + "%")); //se tiver, calcula considerando o filtro do user especificando ate o numero apos a virgula
            } else {
                predicates.add(criteriaBuilder.like(root.get("price").as(String.class), this.price.intValue() + "%")); //se não tiver numero apos a virgula procura considerando somente o numero inteiro
            }
        }

        if(this.nameCategory != null && !this.nameCategory.isEmpty()) {
            Join<Product, Category> categoryJoin = root.join("categories", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), nameCategory));
        }

        Predicate[] predicate = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(predicate));
    }
}
