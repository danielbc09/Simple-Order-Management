package com.dany.order.management.order.dto;

import com.dany.order.management.customer.dto.CustomerDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderDTO {
    private Integer id;
    @NotNull
    private double orderNumber;
    @NotNull
    private int customerId;
    private CustomerDTO customer;
    @NotNull
    private List<OrderProductDTO> orderProducts;
    private String paymentType;
    private double totalPrice;

}
