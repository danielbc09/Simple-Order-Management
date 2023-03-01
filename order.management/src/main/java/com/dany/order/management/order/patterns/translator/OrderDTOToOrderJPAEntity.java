package com.dany.order.management.order.patterns.translator;

import com.dany.order.management.customer.dto.CustomerDTO;
import com.dany.order.management.customer.repository.entity.Customer;
import com.dany.order.management.order.dto.OrderDTO;
import com.dany.order.management.order.dto.OrderProductDTO;
import com.dany.order.management.order.repository.entities.Order;
import com.dany.order.management.order.repository.entities.OrderProduct;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderDTOToOrderJPAEntity implements Translator<OrderDTO, Order> {

    @Override
    public Order translate(OrderDTO orderDTO) {

        return Order.builder()
                .orderNumber(orderDTO.getOrderNumber())
                .date(new Date())
                .paymentType(orderDTO.getPaymentType())
                .orderProducts(getListOfProducts(orderDTO.getOrderProducts()))
                .paymentType(orderDTO.getPaymentType())
                .customer(getCustomer(orderDTO.getCustomer()))
                .build();
    }
    private List<OrderProduct> getListOfProducts(List<OrderProductDTO> productsDTO) {
        return productsDTO.stream().map(this::getOrderProduct).toList();
    }
    private OrderProduct getOrderProduct(OrderProductDTO productDTO){
       return new OrderProduct(productDTO);
    }
    private Customer getCustomer(@NotNull CustomerDTO customerDTO){
        if(customerDTO != null) {
            return new Customer(customerDTO);
        }
        return null;
    }
}
