package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.portfolio.entity.User;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repository.UserRepo;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEnc;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User" , "Email",0));
		return new CustomUserDetails(user);
		
	}

}
