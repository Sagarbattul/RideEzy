package com.mycode.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public ApiResponse<Category> createCategory(@Valid @RequestBody Category category ){
		this.categoryService.createCategory(category);
		return new ApiResponse(category,"Category added successfully.",true,201);
	}
	
	
	//delete category by id
		@DeleteMapping("/deleteCategory/{categoryId}")
		public ApiResponse<Category> deleteCategory(@PathVariable Integer categoryId)
		{
			this.categoryService.deleteCategory(categoryId);
			//return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !", true), HttpStatus.OK);
			return new ApiResponse("Category deleted successfully.",true,200);
		}
	
	
	//get category by id
		@GetMapping("/categories/{categoryId}")
		public ApiResponse<Category> getCategoryById(@PathVariable Integer categoryId)
		{
			Category category = this.categoryService.getCategoryById(categoryId);
			return new ApiResponse(category,"Category found",true,200);
		}
		
		//get all categories
		@GetMapping("/getAllCategories")
		public ApiResponse<List<Category>> getAllCategories()
		{
			List<Category> categories = this.categoryService.getAllCategories();
			return new ApiResponse<>(categories, "categories found", true, 200);
			
		}
//		@GetMapping("/")
//		public ResponseEntity<List<CategoryDto>> getCategories()
//		{
//			List<CategoryDto> categories = this.categoryService.getCategories();
//			return ResponseEntity.ok(categories);
//		}
	
}
