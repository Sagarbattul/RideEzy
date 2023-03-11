package com.mycode.blog.payloads;

import org.springframework.security.core.userdetails.UserDetails;

import com.mycode.blog.controllers.AuthController;
import com.mycode.blog.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtAuthResponse<T> {
	
	private String token;
	
	private T data;

	
	
	

	
}
