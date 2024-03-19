package com.clothes.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.entity.Category;
import com.clothes.shop.repository.CategoryRepository;
import com.clothes.shop.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	/*********************GET ALL CATEGORY**********************/
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<Category>> getAllCategory(){
		List <Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok().body(categories);
	}
	
	/*********************GET CATEGORY BY ID**********************/
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<Category> getByCategoryId(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(category);
	}
	/*********************GET CATEGORY BY TYPE**********************/
	
	@GetMapping(value = ("/categoryName/{categoryName}"))
	public ResponseEntity<Boolean> getByCategoryName(
			@PathVariable("categoryName")String categoryName){
		Category category = categoryService.getByCategoryName(categoryName);
		
		if(category == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

}
