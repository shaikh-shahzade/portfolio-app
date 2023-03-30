package com.portfolio.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public  ResponseEntity<UserDto> getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id", id));
		return new ResponseEntity<UserDto>(userToUserDto(user) , HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<UserDto> createUser(UserDto userDto , String role) {
		User u = userDtoToUser(userDto);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		User user = userRepo.save(u);
		return new ResponseEntity<UserDto>( userToUserDto(user) , HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<UserDto>> getUsers() {
		
		List<UserDto> userDto = userRepo.findAll().stream()
				.map(user	 -> userToUserDto(user)).collect(Collectors.toList());
		
		return new ResponseEntity<List<UserDto>>(userDto , HttpStatus.ACCEPTED);
	}
	@Override
	public ResponseEntity<UserDto> updateUser(Integer id, UserDto userDto) {
		
		User user = userRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User" , "Id" ,id ));
		user = userDtoToUser(userDto);
		user.setId(id);
		user = userRepo.save(user);
		return new ResponseEntity<UserDto>(userToUserDto(user) , HttpStatus.ACCEPTED  );
	}
	@Override
	public ResponseEntity<UserDto> deleteUser(Integer id) {
		
		User user = userRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User" , "Id" ,id ));
		userRepo.delete(user);
		return new ResponseEntity<UserDto>(userToUserDto(user) , HttpStatus.ACCEPTED  );
	
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
