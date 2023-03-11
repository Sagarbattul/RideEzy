package com.mycode.blog.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.User;


@Service
public interface UserService {
	
	 
	
	
	 
	ApiResponse<User> findByEmail(String email);
	
	//User findByEmail(String email);
	 
	 
	 
	 
	 
	
	 
	 ApiResponse<User> registerNewUser_2(User user);







	User updateUser(@Valid User user, Integer userId);

	User getUserById(Integer userId);
	 
}
