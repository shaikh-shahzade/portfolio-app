package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.entity.Review;


public interface ReviewRepo extends JpaRepository<Review,Integer>{

}
