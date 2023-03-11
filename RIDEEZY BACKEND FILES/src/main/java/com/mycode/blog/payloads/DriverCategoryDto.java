package com.mycode.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DriverCategoryDto {

private int d_categoryId;
	
	@NotBlank
	@Size(min = 4, message="min size of category title is 4")
	private String d_categoryTitle;
	
	@NotBlank
	@Size(min = 10, message="min size of description is 10")
	private String d_categoryDescription;
}
