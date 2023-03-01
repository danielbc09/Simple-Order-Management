package com.dany.order.management.order.patterns.translator;

import com.dany.order.management.customer.dto.CustomerDTO;
import com.dany.order.management.customer.repository.entity.Customer;
import com.dany.order.management.order.dto.OrderDTO;
import com.dany.order.management.order.dto.OrderProductDTO;
import com.dany.order.management.order.repository.entities.Order;
import com.dany.order.management.order.repository.entities.OrderProduct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderToOrderDTO implements Translator<Order, OrderDTO> {

    @Override
    public OrderDTO translate(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderProducts(getOrderProductsDTO(order.getOrderProducts()))
                .customer(getCustomerDTO(order.getCustomer()))
                .totalPrice(order.getTotalOrderPrice())
                .build();
    }

    private List<OrderProductDTO> getOrderProductsDTO(List<OrderProduct> orderProducts) {
        return orderProducts.stream().map(this::getOrderProductDTO).toList();
    }

    private OrderProductDTO getOrderProductDTO(OrderProduct orderProduct){
        return new OrderProductDTO(orderProduct);
    }
    private CustomerDTO getCustomerDTO(Customer customer){
        return new CustomerDTO(customer);
    }
}
