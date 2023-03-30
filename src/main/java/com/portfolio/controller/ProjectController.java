
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
	public ResponseEntity<ProjectDto> getProject(@PathVariable(value="id") int id)
	{
		return projectSevice.getProject(id) ; 
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProjectDto>> getAllProject()
	{
		return projectSevice.getAllProject() ; 
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
			@PathVariable(value="id") int id ,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		return projectSevice.updateProject(id , projectDto , userId , categoryId) ; 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ProjectDto> deleteProject(@PathVariable(value="id") int id)
	{
		return projectSevice.deleteProject(id) ; 
	}
	
}
