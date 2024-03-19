package com.clothes.shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.dto.ShirtDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Shirt;
import com.clothes.shop.enumType.SizeEnum;
import com.clothes.shop.repository.ShirtRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.ShirtService;

@RestController
@RequestMapping("shirt")
public class ShirtController {
	
	@Autowired
	ShirtService shirtService;
	
	@Autowired
	ShirtRepository shirtRepository;
	
	@Autowired
	CategoryService categoryService;
	
	/*********************GET  SHIRTS BY CATEGORY**********************/
	
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<?> getShirtByCategory(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		
		if(category == null) {
			return ResponseEntity.badRequest().body("Category is invalid");
		}
		List<ShirtDto> shirtList =shirtService.getAllShirtByCategory(category);
		return ResponseEntity.ok().body(shirtList);
	}
	
	/*********************GET ALL SHIRTS**********************/
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<ShirtDto>> getAllShirt(){
		return ResponseEntity.ok().body(shirtService.getAllShirt());	
	}
	
	/*********************GET SHIRTS BY ID**********************/
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<ShirtDto> getShirtById(@PathVariable int id){
		ShirtDto shirtDto = shirtService.getShirt(id);
		
		if(shirtDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(shirtDto);
	}
	
	/*********************GET ALL SHIRTTYPE**********************/
	
	@GetMapping(value = "/name")
	public ResponseEntity <List<Shirt>> getAllShirtType(@RequestParam String shirtType){
		return ResponseEntity.ok().body(shirtService.getAllByShirtType(shirtType));		
	}
	
	/*********************GET BY SHIRTTYPE**********************/
	
	@GetMapping(value = "/shirtType/{shirtType}")
	public ResponseEntity<Boolean> getShirtType(@PathVariable("shirtType")String shirtType){
		Shirt shirt = shirtService.getByShirtType(shirtType);
		if(shirt == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}
	
	/*********************GET SIZE**********************/
	
	@GetMapping(value = "/size")
	public SizeEnum[] getShirtSize() {
		return SizeEnum.values();
	}
}
