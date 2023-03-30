package com.portfolio.payload;


import java.util.ArrayList;
import java.util.List;

import com.portfolio.entity.Project;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	
	@NotEmpty(message="Name cannot be empty")
	private String name;
	
	@Email
	@NotEmpty(message="Email cannot be empty")
	private String email;
	@NotEmpty(message="Password cannot be empty")
	@Size(min = 5 , message="Password cannnot be less than 5 characters")
	private String password;
	private String about;
	
}
