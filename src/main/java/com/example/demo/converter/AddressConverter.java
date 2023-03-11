package com.example.demo.converter;

import com.example.demo.dto.AddressRequest;
import com.example.demo.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address convertToAddress(AddressRequest addressRequest) {

          Address address = new Address();
          address.setCity(addressRequest.getCity());
          address.setStreet(addressRequest.getStreet());
          address.setStreetNumber(addressRequest.getNumber());

          return address;

    }
}
