package com.portfolio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.portfolio.payload.CategoryDto;

public interface CategoryService {

	public ResponseEntity<CategoryDto> getCategory(int id);
	
	public ResponseEntity<List<CategoryDto>> getAllCategory();
	
	public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto);
	
	public ResponseEntity<CategoryDto> updateCategory(int id ,CategoryDto categoryDto);
	
	public ResponseEntity<CategoryDto> deleteCategory(int id);
	
}
