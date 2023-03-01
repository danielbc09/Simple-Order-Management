package com.dany.order.management.customer.services;


import com.dany.order.management.commons.exceptions.EntityNotExistException;
import com.dany.order.management.customer.dto.CustomerDTO;
import com.dany.order.management.customer.repository.CustomerRepository;
import com.dany.order.management.customer.repository.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getCustomers() {
        return  customerRepository.findAll().stream().map(CustomerService::getDtoFromCustomer).toList();
    }

    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = getCustomerFromDTO(customerDTO);
        customerRepository.save(customer);
    }

    public CustomerDTO getCustomerById(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        validateIfUserExists(customerId, optionalCustomer);
        return  new CustomerDTO(optionalCustomer.get());
    }

    public void updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        validateIfUserExists(customerId, optionalCustomer);
        Customer newCustomer = getCustomerFromDTO(customerDTO);
        newCustomer.setId(customerId);
        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        validateIfUserExists(customerId, optionalCustomer);
        customerRepository.deleteById(customerId);
    }
    private static CustomerDTO getDtoFromCustomer(Customer customer) {
        return new CustomerDTO(customer);
    }

    private static Customer getCustomerFromDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO);
    }

    private static void validateIfUserExists(Integer customerId, Optional<Customer> optionalCustomer) {
        if (optionalCustomer.isEmpty()){
            throw new EntityNotExistException("Customer with id:  " + customerId + " does not exist");
        }
    }
}
