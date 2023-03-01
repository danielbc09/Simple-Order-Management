package com.dany.order.management.order.services;

import com.dany.order.management.commons.exceptions.EntityNotExistException;
import com.dany.order.management.customer.repository.CustomerRepository;
import com.dany.order.management.customer.repository.entity.Customer;
import com.dany.order.management.order.dto.OrderDTO;
import com.dany.order.management.order.patterns.translator.Translator;
import com.dany.order.management.order.repository.OrderProductRepository;
import com.dany.order.management.order.repository.OrderRepository;
import com.dany.order.management.order.repository.entities.Order;
import com.dany.order.management.order.repository.entities.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Translator<OrderDTO, Order> orderDTOToOrder;

    @Autowired
    private Translator<Order, OrderDTO> orderToOrderDTO;
    public void saveOrder(OrderDTO orderDTO) {

        Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
        validateIfUserExists(optionalCustomer);

        Order order = orderDTOToOrder.translate(orderDTO);


        order.setCustomer(optionalCustomer.get());
        Order savedOrder = orderRepository.save(order);

        List<OrderProduct> orderProducts = order.getOrderProducts();

        for(OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(savedOrder);
            orderProductRepository.save(orderProduct);
        }
    }
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return  orders.stream().map(order-> orderToOrderDTO.translate(order)).toList();
    }

    private static void validateIfUserExists(Optional<Customer> optionalCustomer) {
        if (optionalCustomer.isEmpty()){
            throw new EntityNotExistException("Invalid order creation there is no customer");
        }
    }

}
