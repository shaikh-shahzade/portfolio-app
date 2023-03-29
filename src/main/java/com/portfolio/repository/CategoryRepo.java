package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
