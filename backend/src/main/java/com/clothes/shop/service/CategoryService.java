package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.entity.Category;

public interface CategoryService {
	
	public Category createCategory(Category category);
	public List<Category> getAllCategories();
	public Category getCategory(int id);
	public Category updateCategory(int id,Category category);
	public Boolean deleteCategory(int id);
	public Category getByCategoryName(String categoryName);

}
