package com.clothes.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.entity.Category;
import com.clothes.shop.repository.CategoryRepository;
import com.clothes.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(int id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category updateCategory(int id, Category category) {
		Category originalCategory = this.getCategory(id);
		if(originalCategory == null) {
			return null;
		}
		originalCategory.setCategoryId(id);
		originalCategory.setCategoryName(category.getCategoryName());
		return categoryRepository.save(originalCategory);
	}

	@Override
	public Boolean deleteCategory(int id) {
		Category category = this.getCategory(id);
		if(category == null) {
			return false;
		}
		categoryRepository.delete(category);
		return true;
	}

	@Override
	public Category getByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

}
