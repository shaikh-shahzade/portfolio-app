package com.portfolio.payload;

import com.portfolio.entity.Category;
import com.portfolio.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

	private int id;
	
	@NotEmpty(message = "Title cannot be Empty")
	@Size(min = 5 , message = "Title must be greater than 5 characters")
	private String title;
	
	private String image;
	private String link;
	@NotEmpty(message="Content must not be empty")
	@Size(min=10 , message="Content mus be greater than 10 characters")
	private String content;

	private UserDto user;
	
	private CategoryDto category;
	
	
}
