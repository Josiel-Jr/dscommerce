package com.example.dscommerce_new.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_product")
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Column(name = "image_url")
    private String imageUrl;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderItem> orderItems = new ArrayList<>();
}
