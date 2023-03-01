package com.dany.order.management.order.repository.entities;

import com.dany.order.management.customer.repository.entity.Customer;
import com.dany.order.management.order.dto.OrderProductDTO;
import com.dany.order.management.product.repository.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "order_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;
    @Column(nullable = false)
    private Integer quantity;

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public OrderProduct(OrderProductDTO orderProductDto) {
        this.id = orderProductDto.getId();
        this.product = new Product(orderProductDto.getProduct());
        this.quantity = orderProductDto.getQuantity();
    }

}
