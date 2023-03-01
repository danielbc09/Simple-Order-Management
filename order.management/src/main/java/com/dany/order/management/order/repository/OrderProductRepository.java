package com.dany.order.management.order.repository;

import com.dany.order.management.order.repository.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository  extends JpaRepository<OrderProduct, Integer> {
}

