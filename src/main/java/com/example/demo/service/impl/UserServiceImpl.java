package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, AddressService addressService, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.userConverter = userConverter;
    }

    @Override
    public User save(User user) {
        try {
            List<Address> addresses = user.getAddresses();
            for (Address address : addresses) {
                addressService.save(address);
            }
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateRecordException(
                    String.format("User: %s already exists.", user.getName()));
        }
    }

    @Override
    public User register(UserRequest userRequest) {
        User registeredUser = userConverter.convertToUser(userRequest);
        userRepository.save(registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No such User with this id: " + id));

    }

    @Override
    public User updateUser(UserRequest userRequest, Long id) {
        User user = findById(id);
        User updatedUser = userConverter.update(userRequest, user);
        userRepository.save(updatedUser);
        return updatedUser;
    }


    @Override
    public void deleteById(Long id) {
        Long found = findById(id).getId();
        userRepository.deleteById(found);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

//    @PostConstruct
//    public void init() {
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
}

