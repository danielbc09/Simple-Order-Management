package com.dany.order.management.customer.controller;

import com.dany.order.management.commons.APIResponse;
import com.dany.order.management.customer.dto.CustomerDTO;
import com.dany.order.management.customer.services.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<CustomerDTO> body = customerService.getCustomers();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") Integer customerId) {
        CustomerDTO body = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addCustomer(@RequestBody  CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(new APIResponse(true, "Customer created"), HttpStatus.CREATED);

    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<APIResponse> updateCustomer(@PathVariable("customerId") Integer customerId,
                                                      @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerId, customerDTO);
        return new ResponseEntity<>(new APIResponse(true, "Customer updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<APIResponse> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(new APIResponse(true, "Customer updated"), HttpStatus.OK);
    }

}
