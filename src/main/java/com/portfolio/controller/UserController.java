package com.portfolio.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
@GetMapping("getuser")
public ResponseEntity<UserDto> getUser( @RequestParam("id") Integer id)
{
	return  new ResponseEntity<UserDto>( userService.getUserById(id) , HttpStatus.OK);
}

@PostMapping("create")
public ResponseEntity<UserDto>  createUser(  @Valid @RequestBody UserDto userDto)
{
		return  new ResponseEntity<UserDto>(userService.createUser(userDto) , HttpStatus.CREATED);
}

}
