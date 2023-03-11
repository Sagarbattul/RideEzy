package com.mycode.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycode.blog.entities.User;


public interface UserRepo extends JpaRepository<User, Integer>{
		
	Optional<User> findByEmail(String email);
	
}	
