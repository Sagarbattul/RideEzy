package com.mycode.blog.services;

import java.util.List;

import javax.validation.Valid;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.DriverCategory;

public interface DriverCategoryService {

	ApiResponse<Category> createDriverCategory(@Valid DriverCategory driverCategory);

	void deleteDriverCategory(Integer d_categoryId);

	DriverCategory getDriverCategoryById(Integer d_categoryId);

	List<DriverCategory> getAllDriverCategories();
	

}
