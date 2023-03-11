package com.mycode.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.User;
import com.mycode.blog.exceptions.ApiException;
import com.mycode.blog.payloads.JwtAuthRequest;
import com.mycode.blog.payloads.JwtAuthResponse;

import com.mycode.blog.security.JwtTokenHelper;
import com.mycode.blog.services.UserService;

import io.swagger.models.Response;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest jwtAuthRequest
			) throws Exception
	{
		this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		//UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		ApiResponse<User> userApiResponse = (ApiResponse<User>) userService.findByEmail(jwtAuthRequest.getUsername());
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		
		
		if(userApiResponse.isStatus())
		{
		String token = this.jwtTokenHelper.generateToken(userApiResponse.getData());
		
		jwtAuthResponse.setToken(token);
		jwtAuthResponse.setData(userApiResponse.getData());
		//jwtAuthResponse.setData(userDetails);
		//UserDetails userDetails2 = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//jwtAuthResponse.setData(userDetails2);
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	private void authenticate(String username, String password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Invalid Details !!");
			throw new ApiException("invalid username or password");
		}
		
	}
	
	
	//register new user api
//	@PostMapping("/register")
//	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
//	{
//		UserDto registeredUser = this.userService.registerNewUser(userDto);
//		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
//	}
	
	
	//register new user api
		@PostMapping("/register")
		public ResponseEntity<ApiResponse<User>> registerNew(@RequestBody User userDto)
		{
			ApiResponse<User> registeredUser = this.userService.registerNewUser_2(userDto);
			if(registeredUser.isStatus())
			return new ResponseEntity<ApiResponse<User>>(registeredUser, HttpStatus.CREATED);
			else return new ResponseEntity<ApiResponse<User>>(registeredUser, HttpStatus.EXPECTATION_FAILED);
		}
}
