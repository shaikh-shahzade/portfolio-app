package com.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.payload.CategoryDto;
import com.portfolio.service.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id)
	{
		return categoryService.getCategory(id);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		return categoryService.getAllCategory();
	}
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		return categoryService.createCategory(categoryDto);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(
			@RequestBody CategoryDto categoryDto,
			@PathVariable Integer id)
	{
		return categoryService.updateCategory(id , categoryDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Integer id)
	{
		return categoryService.deleteCategory(id);
	}
}
