package com.portfolio.payload;

import java.util.ArrayList;
import java.util.List;

import com.portfolio.entity.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	@NotEmpty(message = "ID cannot be Empty")
	private int id;
	
	@NotEmpty(message = "Title cannot be Empty")
	@Size(min = 5 , message = "Title must be greater than 5 characters")
	private String title;
	
	@NotEmpty(message = "Description cannot be Empty")
	@Size(min = 10 , message = "Description must be greater than 10 characters")
	private String description;
	
}
