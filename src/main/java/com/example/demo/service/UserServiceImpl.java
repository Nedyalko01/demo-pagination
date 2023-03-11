package com.example.demo.service;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

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

        List<Address> addresses = user.getAddresses();

        for (Address address : addresses) {
            addressService.save(address);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No such User"));
    }

    @Override
    public User updateUser(Long id, UserRequest userRequest) {

        User user = findById(id);

        User updatedUser = userConverter.update(user, userRequest);

        userRepository.save(updatedUser);

        return updatedUser;
    }

    @Override
    public void deleteById(Long id) {
       userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
