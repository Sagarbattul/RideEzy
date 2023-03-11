package com.mycode.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.Vehicle;


public interface CategoryService {

	
	//create
	ApiResponse<Category> createCategory(Category category);
		
		//update
	ApiResponse<Category> updateCategory( Category category, Integer categoryId);
		
		//delete
		public void deleteCategory(Integer categoryId);
		
		//get category by id
	Category getCategoryById(Integer categoryId);
		
		
		
		
		//get all catgories
		List<Category> getAllCategories();

		
}
