package com.example.demo.dto;

import com.example.demo.entity.Address;

import java.util.List;
import java.util.Set;

public class UserRequest {

    private String name;
    private String email;

    private List<AddressRequest> addressRequests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressRequest> getAddressRequests() {
        return addressRequests;
    }

    public void setAddressRequests(List<AddressRequest> addressRequests) {
        this.addressRequests = addressRequests;
    }
}


