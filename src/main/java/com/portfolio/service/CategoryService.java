package com.portfolio.service;

import org.springframework.http.ResponseEntity;

import com.portfolio.payload.CategoryDto;

public interface CategoryService {

	
	public ResponseEntity<CategoryDto> getCategory(int id);
	
	public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto);
}
