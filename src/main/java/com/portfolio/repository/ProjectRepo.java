package com.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;

import com.portfolio.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

	public Page<Project> findAll(Pageable pageable);
}
