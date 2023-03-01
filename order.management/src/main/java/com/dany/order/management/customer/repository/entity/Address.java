package com.dany.order.management.customer.repository.entity;

import com.dany.order.management.customer.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String street;
    private int number;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @OneToMany( mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customer;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.getStreet();
        this.number = addressDTO.getNumber();
        this.city = addressDTO.getCity();
        this.state = addressDTO.getState();
        this.zipCode = addressDTO.getZipCode();
        this.country  = addressDTO.getCountry();
    }

}
