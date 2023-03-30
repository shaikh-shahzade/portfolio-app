package com.portfolio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portfolio.entity.Category;
import com.portfolio.entity.Project;
import com.portfolio.entity.User;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.payload.ProjectDto;
import com.portfolio.payload.UserDto;
import com.portfolio.repository.CategoryRepo;
import com.portfolio.repository.ProjectRepo;
import com.portfolio.repository.UserRepo;
import com.portfolio.service.ProjectSevice;

@Service
public class ProjectServiceImpl implements ProjectSevice{

	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public ResponseEntity<ProjectDto> getProject(int id) {
		Project project = projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "ID", id));
		return new ResponseEntity<ProjectDto>(pToDto(project),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProjectDto>> getAllProject() {
		List<ProjectDto> projectdtos = projectRepo.findAll().stream()
				.map(project -> pToDto(project) ).collect(Collectors.toList());
		return new ResponseEntity<List<ProjectDto>>(projectdtos,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProjectDto> createProject(ProjectDto projectDto , Integer userId  , Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
		Project project = dtoToP(projectDto);
		project.setUser(user);
		project.setCategory(category);
		project = projectRepo.save(project);
		return new ResponseEntity<ProjectDto>(pToDto(project),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ProjectDto> updateProject(int id , ProjectDto projectDto , Integer userId , Integer categoryId) {
		Project project = projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "ID", id));
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
		project = dtoToP(projectDto);
		project.setCategory(category);
		project.setUser(user);
		project.setId(id);
		projectDto = pToDto(projectRepo.save(project));
		return new ResponseEntity<ProjectDto>(projectDto,HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<ProjectDto> deleteProject(int id) {
		Project project = projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "ID", id));
		projectRepo.delete(project);
		projectRepo.flush();
		return new ResponseEntity<ProjectDto>(pToDto(project),HttpStatus.OK);
		}

	ProjectDto pToDto(Project project)
	{
		return modelMapper.map(project,ProjectDto.class);
	}
	
	Project dtoToP(ProjectDto projectDto)
	{
		return modelMapper.map(projectDto,Project.class);
	}
}