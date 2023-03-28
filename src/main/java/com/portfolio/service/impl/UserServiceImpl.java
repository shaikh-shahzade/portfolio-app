package com.portfolio.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portfolio.entity.User;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.payload.UserDto;
import com.portfolio.repository.UserRepo;
import com.portfolio.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public  UserDto getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id", id));
		return userToUserDto(user);
	}
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User u = userDtoToUser(userDto);
		User user = userRepo.save(u);
		
		return userToUserDto(user);
	}
	
	public User userDtoToUser(UserDto userDto)
	{
		return modelMapper.map(userDto,User.class);
	}
	
	public UserDto userToUserDto(User user)
	{
		return  modelMapper.map(user, UserDto.class);
	}

	

}
