package com.portfolio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.portfolio.payload.UserDto;


public interface UserService {

	ResponseEntity<UserDto> createUser(UserDto user , String role);

	ResponseEntity<UserDto> getUserById(Integer id);
	
	ResponseEntity<List<UserDto>> getUsers();
	
	ResponseEntity<UserDto> updateUser(Integer id , UserDto userDto);
	
	ResponseEntity<UserDto> deleteUser(Integer id);
}
