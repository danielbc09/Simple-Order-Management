package com.dany.order.management.product.dto;

import com.dany.order.management.product.repository.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private double price;
    @JsonIgnore
    private double weight;
    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setWeight(product.getWeight());
    }
}
