package com.portfolio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.portfolio.payload.ProjectDto;

public interface ProjectSevice {

	public ResponseEntity<ProjectDto> getProject(int id);
	
	public ResponseEntity<List<ProjectDto>> getAllProject(int page) ;
	
	public ResponseEntity<ProjectDto> createProject(ProjectDto projectDto , Integer userId , Integer categoryId);
	
	public ResponseEntity<ProjectDto> updateProject(int id , ProjectDto projectDto  , Integer userId , Integer categoryId);
	
	public ResponseEntity<ProjectDto> deleteProject(int id);
	
}
