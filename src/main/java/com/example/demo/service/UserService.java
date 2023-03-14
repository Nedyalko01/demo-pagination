package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserService {

  User save (User user);

  User register(UserRequest userRequest);

  List<User> getAll();

  User findById(Long id);

  User updateUser(UserRequest userRequest, Long id);

  void deleteById(Long id);

  Page<User> findAll(Pageable pageable);

}
