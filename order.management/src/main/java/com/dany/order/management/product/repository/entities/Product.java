package com.dany.order.management.product.repository.entities;


import com.dany.order.management.order.repository.entities.OrderProduct;
import com.dany.order.management.product.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private  String name;

    private double price;

    @JsonIgnore
    private double weight;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();


    public Product (ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.weight = productDto.getWeight();
    }
}
