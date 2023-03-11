package com.mycode.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.Vehicle;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.repositories.CategoryRepo;
import com.mycode.blog.services.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public ApiResponse<Category> createCategory(Category category) {
		// TODO Auto-generated method stub
		this.categoryRepo.save(category);
		return null;
	}

	@Override
	public ApiResponse<Category> updateCategory(Category category, Integer categoryId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		this.categoryRepo.delete(category);
		
	}
	
	

	
	
	

	private ApiResponse<Category> ApiResponse(Category category, String string, boolean b, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Category getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoy id", categoryId));
		
		
		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		return categories.stream().collect(Collectors.toList());
	}
	
	
//	@Override
//	public List<CategoryDto> getCategories() {
//		List<Category> categories = this.categoryRepo.findAll();
//		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
//		
//		return catDtos;
//	}
	
	

}
