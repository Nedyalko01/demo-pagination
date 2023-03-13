package com.example.demo.controller;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserConverter userConverter;

    public UserController(UserServiceImpl userServiceImpl, UserConverter userConverter) {
        this.userServiceImpl = userServiceImpl;
        this.userConverter = userConverter;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody @NotNull UserRequest userRequest) {

        User user = userConverter.convertToUser(userRequest);

        User savedUser = userServiceImpl.save(user);

        UserResponse response = userConverter.convertUserToResponse(savedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {

        List<UserResponse> users = userServiceImpl.getAll()
                .stream()
                .map(userConverter::convertUserToResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @NotNull UserRequest userRequest, @PathVariable Long id) {

        User user = userServiceImpl.updateUser(id, userRequest);

        UserResponse response = userConverter.convertUserToResponse(user);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        User user = userServiceImpl.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse response = userConverter.convertUserToResponse(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/sort-one")
    public Page<User> getAllUsersSorted(@RequestParam int page, @RequestParam int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return userServiceImpl.findAll(pageRequest);

    }

    @GetMapping(value = "/sort-two")
    public Page<User> getAllUsersSortedById(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {

        Page<User> sorted = userServiceImpl.findAll(PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("id")));

        return sorted;
    }
}