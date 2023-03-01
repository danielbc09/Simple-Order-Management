package com.dany.order.management.order.controller;


import com.dany.order.management.commons.APIResponse;
import com.dany.order.management.order.dto.OrderDTO;
import com.dany.order.management.order.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addOrder(@RequestBody @Validated OrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
        return new ResponseEntity<>(new APIResponse(true, "Product created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderDTO>>  getOrders() {
        List<OrderDTO> body = orderService.getOrders();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
