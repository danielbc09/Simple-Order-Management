package com.dany.order.management.order.dto;

import com.dany.order.management.order.repository.entities.OrderProduct;
import com.dany.order.management.product.dto.ProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProductDTO {

    private Integer id;
    @NotNull
    ProductDto product;
    @NotNull
    Integer quantity;

    public OrderProductDTO(OrderProduct orderProductDTO) {
        this.id = orderProductDTO.getId();
        this.product = new ProductDto(orderProductDTO.getProduct());
        this.quantity = orderProductDTO.getQuantity();
    }
}
