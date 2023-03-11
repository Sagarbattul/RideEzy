package com.mycode.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.DriverCategory;
import com.mycode.blog.services.DriverCategoryService;

@RestController
@RequestMapping("/api/d_categories")
public class DriverCategoryController {

	@Autowired
	private DriverCategoryService driverCategoryService;
	
	//create a driver category
	@PostMapping("/createDriverCategory")
	public ApiResponse<DriverCategory> createDriverCategory(@Valid @RequestBody DriverCategory driverCategory)
	{
		this.driverCategoryService.createDriverCategory(driverCategory);
		return new ApiResponse(driverCategory, "driver category added successfully", true, 201);
		
	}
	
	@DeleteMapping("/deleteDriverCategory/{d_categoryId}")
	public ApiResponse<DriverCategory> deleteDriverCategory(@PathVariable Integer d_categoryId)
	{
		this.driverCategoryService.deleteDriverCategory(d_categoryId);
		return new ApiResponse<>("Driver Category deleted successfully", true, 200);
		
	}
	
	//get driver category by id
	@GetMapping("/getDriverCategoryById/{d_categoryId}")
	public ApiResponse<DriverCategory> getDriverCategoryById(@PathVariable Integer d_categoryId)
	{
		DriverCategory driverCategory = this.driverCategoryService.getDriverCategoryById(d_categoryId);
		return new ApiResponse(driverCategory, "driver category found", true, 200);
		
	}

	@GetMapping("/getAllDriverCategories")
	public ApiResponse<List<DriverCategory>> getAllDriverCategories()
	{
		List<DriverCategory> allDriverCategories = this.driverCategoryService.getAllDriverCategories();
		return new ApiResponse<>(allDriverCategories, "driver categories found", true, 200);
	}

}
