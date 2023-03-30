package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portfolio.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	@Query("SELECT u FROM User u WHERE u.email =:email")
	public Optional<User> findByEmail(@Param("email") String email);
}
