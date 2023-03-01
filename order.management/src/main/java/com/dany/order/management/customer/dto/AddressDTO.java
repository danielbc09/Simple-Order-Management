package com.dany.order.management.customer.dto;

import com.dany.order.management.customer.repository.entity.Address;
import com.dany.order.management.product.repository.entities.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    private Integer id;
    private String street;
    private int number;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public AddressDTO(Address address) {
        this.setId(address.getId());
        this.setStreet(address.getStreet());
        this.setCity(address.getCity());
        this.setCity(address.getCity());
        this.setZipCode(address.getZipCode());
        this.setCountry(address.getCountry());
        this.setState(address.getState());
    }

}
