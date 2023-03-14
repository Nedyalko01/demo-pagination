package com.example.demo.service;

import com.example.demo.entity.Address;

import java.util.List;

public interface AddressService {

    Address save (Address address);

    List<Address> getAll();

}
