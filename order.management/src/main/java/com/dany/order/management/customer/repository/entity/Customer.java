package com.dany.order.management.customer.repository.entity;

import com.dany.order.management.customer.dto.AddressDTO;
import com.dany.order.management.customer.dto.CustomerDTO;
import com.dany.order.management.order.repository.entities.Order;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Nonnull
    private String name;
    private int phone;
    @Nonnull
    private String email;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.getName();
        this.phone = customerDTO.getPhone();
        this.email = customerDTO.getEmail();
        this.address = getAddress(customerDTO.getAddress());
    }

    private static Address getAddress(AddressDTO addressDTO) {
        return new Address(addressDTO);
    }
}
