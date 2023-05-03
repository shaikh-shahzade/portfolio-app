
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

import com.portfolio.payload.ProjectDto;
import com.portfolio.service.ProjectSevice;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	ProjectSevice projectSevice;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> getProject(@PathVariable int id)
	{
		return projectSevice.getProject(id) ; 
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProjectDto>> getAllProject(
			@RequestParam(required = false , defaultValue = "1") int page ,
			@RequestParam(required = false , defaultValue = "asc") String sort,
			@RequestParam(required = false , defaultValue = "id") String sortby
			
			)
	{
		return projectSevice.getAllProject(page-1 , sort  , sortby) ; 
	}
	
	@GetMapping("search")
	public ResponseEntity<List<ProjectDto>> searchProject(
			@RequestParam(required = false , defaultValue = "1") int page ,
			@RequestParam(required = true) String key
			
			)
	{
		return projectSevice.searchProject(page-1 ,key); 
	}
	
	@PostMapping("/create/user/{userId}/category/{categoryId}")
	public ResponseEntity<ProjectDto> createProject(
			@Valid @RequestBody ProjectDto projectDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			
			)
	{
		return projectSevice.createProject(projectDto ,userId ,categoryId) ; 
	}
	
	@PutMapping("/update/{id}/user/{userId}/category/{categoryId}")
	public ResponseEntity<ProjectDto> updateProject(
			@Valid @RequestBody ProjectDto projectDto , 
			@PathVariable int id ,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		return projectSevice.updateProject(id , projectDto , userId , categoryId) ; 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ProjectDto> deleteProject(@PathVariable int id)
	{
		return projectSevice.deleteProject(id) ; 
	}
	
}
