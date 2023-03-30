package com.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.entity.User;
import com.portfolio.payload.UserDto;
import com.portfolio.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;


@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUsers()
{
	return   userService.getUsers() ;
}


@GetMapping("/{id}")
public ResponseEntity<UserDto> getUser( @PathVariable("id") Integer id)
{
	return   userService.getUserById(id);
}

@PostMapping("/create")
public ResponseEntity<UserDto>  createUser(  
		@Valid @RequestBody UserDto userDto,
		@RequestParam(  value = "role" , defaultValue = "Normal", required = false) String role
		)
{
		return  userService.createUser(userDto , role);
}

@PutMapping("/update/{id}")
public ResponseEntity<UserDto> updateUsers(
		@Valid @RequestBody UserDto userDto ,
		@PathVariable("id") Integer id 
		)
{
	return   userService.updateUser(id , userDto);
}


@DeleteMapping("/delete/{id}")
public ResponseEntity<UserDto> removeUsers( @PathVariable("id") Integer id)
{
	return   userService.deleteUser(id);
}
}
