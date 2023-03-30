package com.portfolio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
		CategoryDto  cdto= categoryToCategoryDto(category);
		return new ResponseEntity<CategoryDto>(cdto,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categoryDtos = categoryRepo.findAll().stream()
				.map(category -> categoryToCategoryDto(category)).collect(Collectors.toList());
		
		return new ResponseEntity<List<CategoryDto>>(categoryDtos , HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {
		Category category = categoryDtoToCategory(categoryDto);
		categoryDto = categoryToCategoryDto(categoryRepo.save(category));
		return new ResponseEntity<CategoryDto>(categoryDto , HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CategoryDto> updateCategory(int id , CategoryDto categoryDto) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Id", id));
		category = categoryDtoToCategory(categoryDto);
		category.setId(id);
		categoryRepo.save(category);
		return new ResponseEntity<CategoryDto>(categoryToCategoryDto(category) , HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<CategoryDto> deleteCategory(int id) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","Id", id));
		categoryRepo.deleteById(id);
		return new ResponseEntity<CategoryDto>(categoryToCategoryDto(category) , HttpStatus.OK);
	}

	CategoryDto categoryToCategoryDto(Category category)
	{
		return modelMapper.map(category , CategoryDto.class);
	}
	
	Category categoryDtoToCategory(CategoryDto categoryDto)
	{
		return modelMapper.map(categoryDto , Category.class);
	}
	
}
