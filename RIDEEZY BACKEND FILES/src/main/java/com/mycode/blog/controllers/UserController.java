package com.mycode.blog.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.User;
import com.mycode.blog.repositories.UserRepo;
import com.mycode.blog.services.FileService;
import com.mycode.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
//	@GetMapping("/getUserByEmail/{email}")
//	public ApiResponse<User> getUserByEmail(@PathVariable String email) {
//		User user = this.userService.findByEmail(email);
//		return  new ApiResponse(email, false, 404);
//	}
	
	//put - update user
	@PutMapping("/{userId}")
	public ApiResponse<User> updateUser(@Valid @RequestBody User user, @PathVariable Integer userId)
	{
	
		User updatedUser = this.userService.updateUser(user, userId);
		return new ApiResponse<>("user updated successfully", true, 200);
		
	}
	
	//get single user by id
	@GetMapping("/getUserById/{userId}")
	public ApiResponse<User> getUserById(@PathVariable Integer userId)
	{
		User user = this.userService.getUserById(userId);
		return new ApiResponse<>(user,"user found with id =" + userId, true, 200);
		
	}
	
	
	//upload user image
	
	@Value("${project.image}")
	private String path;
//	
	@PostMapping("/uploadUserImage/{userId}")
	public ApiResponse<User> uploadUserImage(@RequestParam("image") MultipartFile image, @PathVariable Integer userId) throws IOException
	{
		User user = this.userService.getUserById(userId);
		String fileName = this.fileService.uploadImage(path, image);
		user.setUserImage(fileName);
		User updatedUser = this.userService.updateUser(user, userId);
		
		return new ApiResponse<User>(updatedUser, "user image added successfully", true, 200);
		
	}
//	//vehicle image upload
//	@PostMapping("/uploadVehicleImage/{vehicleId}")
//	public ApiResponse<VehicleDto> uploadVehicleImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId)throws IOException
//	{
//		VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
//		String fileName = this.fileService.uploadImage(path, image);
//		
//		vehicleDto.setVehicleImage(fileName);
//		VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
//		
//		return new ApiResponse(updatedVehicle, "vehicle image added successfully", true, 200);
//		
//	}
	
	
}
