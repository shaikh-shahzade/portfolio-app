package com.portfolio.service;

import com.portfolio.payload.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);

	UserDto getUserById(Integer id);
}
