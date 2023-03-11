package com.mycode.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.DriverCategory;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.repositories.DriverCategoryRepo;
import com.mycode.blog.services.DriverCategoryService;

@Component
public class DriverCategoryServiceImpl implements DriverCategoryService{

	@Autowired
	private DriverCategoryRepo driverCategoryRepo;
	
	@Override
	public ApiResponse<Category> createDriverCategory(@Valid DriverCategory driverCategory) {
		this.driverCategoryRepo.save(driverCategory);
		return null;
	}

	@Override
	public void deleteDriverCategory(Integer d_categoryId) {
		DriverCategory driverCategory = this.driverCategoryRepo.findById(d_categoryId).orElseThrow(()-> new ResourceNotFoundException("driver category", "driver category id", d_categoryId));
		this.driverCategoryRepo.delete(driverCategory);
	}

	@Override
	public DriverCategory getDriverCategoryById(Integer d_categoryId) {
		DriverCategory driverCategory =  this.driverCategoryRepo.findById(d_categoryId).orElseThrow(()-> new ResourceNotFoundException("driver category", "driver categoy id", d_categoryId));
		return driverCategory;
	}

	@Override
	public List<DriverCategory> getAllDriverCategories() {
		List<DriverCategory> driverCategories = this.driverCategoryRepo.findAll();
		return driverCategories.stream().collect(Collectors.toList());
	}
	
	
}
