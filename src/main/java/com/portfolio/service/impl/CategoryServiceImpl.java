package com.portfolio.service.impl;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.entity.Category;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.payload.CategoryDto;
import com.portfolio.repository.CategoryRepo;
import com.portfolio.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<CategoryDto> getCategory(int id) {
		
		Category category = categoryRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category", "Id", id));
		CategoryDto  cdto= modelMapper.map(category, CategoryDto.class);
		return new ResponseEntity<CategoryDto>(cdto,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category SavedCategory = categoryRepo.save(category);
		categoryDto = modelMapper.map(SavedCategory, CategoryDto.class);
		return new ResponseEntity<CategoryDto>(categoryDto , HttpStatus.CREATED);
		
		
	}
	
	
	
}
