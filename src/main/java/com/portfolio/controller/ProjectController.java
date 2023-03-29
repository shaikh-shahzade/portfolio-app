package com.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.payload.ProjectDto;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@GetMapping("post/:{id}")
	public ResponseEntity<ProjectDto> getPost(@PathParam(value="id") int id)
	{
		return null; 
	}
}
