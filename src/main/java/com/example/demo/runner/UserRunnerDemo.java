//package com.example.demo.runners;
//
//import com.example.demo.entity.Address;
//import com.example.demo.entity.User;
//import com.example.demo.service.impl.AddressServiceImpl;
//import com.example.demo.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UserRunnerDemo implements CommandLineRunner {
//
//    private final UserServiceImpl userServiceImpl;
//    private final AddressServiceImpl addressServiceImpl;
//
//    @Autowired
//    public UserRunnerDemo(UserServiceImpl userServiceImpl, AddressServiceImpl addressServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//        this.addressServiceImpl = addressServiceImpl;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setId(1L);
//        user.setName("Test User");
//        user.setEmail("test@email.com");
//
//        userServiceImpl.save(user);
//
//        Address address = new Address();
//        address.setId(1L);
//        address.setCity("NY");
//        address.setStreet("demo_street");
//        address.setNumber(101 - 55);
//        address.setUser(user);
//
//        addressServiceImpl.save(address);
//
//    }
//}
