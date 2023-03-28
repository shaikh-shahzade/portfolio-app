package com.portfolio.service;

import org.springframework.stereotype.Service;

import com.portfolio.payload.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);

	UserDto getUserById(Integer id);
}
