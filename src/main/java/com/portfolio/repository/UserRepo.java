package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
