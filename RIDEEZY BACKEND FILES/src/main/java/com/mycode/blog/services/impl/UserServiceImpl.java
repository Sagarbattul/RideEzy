package com.mycode.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mycode.blog.config.AppConstants;
import com.mycode.blog.entities.ApiResponse;

import com.mycode.blog.entities.User;


import com.mycode.blog.repositories.UserRepo;
import com.mycode.blog.services.UserService;
import com.mycode.blog.utils.Utility;
import com.mycode.blog.exceptions.*;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private RoleRepo roleRepo;

	/*
	 * @Override public UserDto createUser(UserDto userDto) {
	 * 
	 * User user = this.dtoToUser(userDto); User savedUser =
	 * this.userRepo.save(user); return this.UserToDto(savedUser); }
	 */

	/*
	 * @Override public UserDto updateUser(UserDto userDto, Integer userId) {
	 * 
	 * User user = this.userRepo.findById(userId) .orElseThrow(() -> new
	 * ResourceNotFoundException("User", "id", userId));
	 * user.setFirstName(userDto.getFirstName());
	 * user.setLastName(userDto.getLastName()); user.setEmail(userDto.getEmail());
	 * user.setPassword(userDto.getPassword());
	 * user.setAddress(userDto.getAddress()); User updatedUser =
	 * this.userRepo.save(user); UserDto userDto1 = this.UserToDto(updatedUser);
	 * return userDto1;
	 * 
	 * }
	 */

	/*
	 * @Override public UserDto getUserById(Integer userId) { User user =
	 * this.userRepo.findById(userId) .orElseThrow(() -> new
	 * ResourceNotFoundException("User", "id", userId)); return
	 * this.UserToDto(user); }
	 */

	/*
	 * @Override public List<UserDto> getAllUsers() { List<User> users =
	 * this.userRepo.findAll(); List<UserDto> userDtos = users.stream().map(user ->
	 * this.UserToDto(user)).collect(Collectors.toList()); return userDtos; }
	 */
	/*
	 * @Override public void deleteUser(Integer userId) { User user =
	 * this.userRepo.findById(userId) .orElseThrow(() -> new
	 * ResourceNotFoundException("User", "id", userId)); this.userRepo.delete(user);
	 * 
	 * }
	 */

	
	/* future need*/
//	private User dtoToUser(UserDto userDto) {
////		User user = new User();
////		user.setId(userDto.getId());
////		user.setName(userDto.getName());
////		user.setEmail(userDto.getEmail());
////		user.setAbout(userDto.getAbout());
////		user.setPassword(userDto.getPassword());
//		
//		//after modelmapper vidoe
//		User user = this.modelMapper.map(userDto, User.class);
//		return user;
//
//	}

	/* future need */
//	public UserDto UserToDto(User user) {
////		UserDto userDto = new UserDto();
////		userDto.setId(user.getId());
////		userDto.setName(user.getName());
////		userDto.setEmail(user.getEmail());
////		userDto.setPassword(user.getPassword());
////		userDto.setAbout(user.getAbout());
//		
//		//after modelmapper video
//		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		return userDto;
//	}

//	@Override
//	public UserDto registerNewUser(UserDto userDto) {
//		User user = this.modelMapper.map(userDto, User.class);
//		//encoded the password
//		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//		
//		//roles
//		 Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
//		 user.getRoles().add(role);
//		  User newUser = this.userRepo.save(user);
//		  
//		
//		return this.modelMapper.map(newUser, UserDto.class);
//	}
	
	

	@Override
	public ApiResponse<User> registerNewUser_2(User userDto) {
		if(userDto==null)
			return new ApiResponse("Input fields are missing !",false,402);
		else if(Utility.isFieldEmpty(userDto.getFirstName()) || userDto.getFirstName().length()<4) {
			return new ApiResponse("FirstName should be greater than 4 characters!",false,402);
		}
		
		else if(Utility.isFieldEmpty(userDto.getLastName()) || userDto.getLastName().length()<4) {
			return new ApiResponse("LastName should be greater than 4 characters!",false,402);
		}
		
				
		//else if(Utility.isFieldEmpty(userDto.getEmail()) || !Utility.isEmailValid(userDto.getEmail()))
		else if(Utility.isEmailValid(userDto.getEmail()))
			return new ApiResponse("Email should be in proper format !",false,402);
		
		else if(Utility.isFieldEmpty(userDto.getPassword()) || (userDto.getPassword().length()<3 || userDto.getPassword().length()>10))
			return new ApiResponse("Password should be more than 3 or less than 10 chars !",false,402);
		
		else if(Utility.isFieldEmpty(userDto.getMobile()) || userDto.getMobile().length()!=10)
			return new ApiResponse<>("Proper Mobile Number not provided", false, 402);
		
//		else if(Utility.isFieldEmpty(userDto.getDob()))
//			return new ApiResponse<>("Date of Birth Not Provided", false, 402);
		
		else if(Utility.isFieldEmpty(userDto.getGender()))
			return new ApiResponse<>("Gender not selected", false, 402);
		
		else if(Utility.isFieldEmpty(userDto.getAddress()))
			return new ApiResponse<>("Address Not Provided", false, 402);
		
			
		Optional<User> returnedUser = userRepo.findByEmail(userDto.getEmail());
		if(returnedUser.isPresent()) {
			return new ApiResponse(userDto,"User already exists !",false,402);
		}
		
		
		//encoded the password
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
				
				//roles
				/*
				 * Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
				 * userDto.getRoles().add(role); User newUser = this.userRepo.save(userDto);
				 * 
				  *TODO Auto-generated method stub*/ 
				this.userRepo.save(userDto);
				  return new ApiResponse(userDto,"User registered successfully.",true,201);
				 
	}

@Override
public ApiResponse<User> findByEmail(String email) {
	Optional<User> returnedUser = userRepo.findByEmail(email);
	
	if(returnedUser.isPresent()) {
		return new ApiResponse<User>(returnedUser.get(),"User found !",true,200);
	}
	User tempUser = new User();
	return new ApiResponse(tempUser,"User Not found !",false,402);
//	return null;
	
}

@Override
public User updateUser(@Valid User user, Integer userId) {
	User user2 = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	user2.setFirstName(user.getFirstName());
	user2.setLastName(user.getLastName());
	user2.setMobile(user.getMobile());
	user2.setGender(user.getGender());
	user2.setAddress(user.getAddress());
	user2.setPassword(this.passwordEncoder.encode(user.getPassword()));
	user2.setDob(user.getDob());
	user2.setCity(user.getCity());
	
	User updatedUser = this.userRepo.save(user2);
	
	return updatedUser;
}


//updateuser code 2
//@Override
//public ApiResponse<User> updateUser(@Valid User user, Integer userId) {
//	User user2 = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//	System.out.println("password from request"+this.passwordEncoder.encode(user.getPassword()));
//	System.out.println("password from server"+ user2.getPassword());
//	if(!user.getPassword().equals(user2.getPassword())) {
//		return new ApiResponse(user,"User password incorrect",false,402);
//	}else
//	{
//	user2.setFirstName(user.getFirstName());
//	user2.setLastName(user.getLastName());
//	user2.setMobile(user.getMobile());
//	user2.setGender(user.getGender());
//	user2.setAddress(user.getAddress());
//	user2.setPassword(this.passwordEncoder.encode(user.getPassword()));
//	user2.setDob(user.getDob());
//	user2.setCity(user.getCity());
//	
//	this.userRepo.save(user2);
//	return new ApiResponse<User>(user,"User updatedsuccessfully",true,200);
//	}
//}


	


@Override
public User getUserById(Integer userId) {
	User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	return user;
}


	


	

}
