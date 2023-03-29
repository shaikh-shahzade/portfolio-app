package com.portfolio.service;

import org.springframework.http.ResponseEntity;

import com.portfolio.payload.ProjectDto;

public interface ProjectSevice {

	public ResponseEntity<ProjectDto> getProject(int id);
	
}
