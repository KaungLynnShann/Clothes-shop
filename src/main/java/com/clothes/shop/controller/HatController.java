package com.clothes.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.dto.HatDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Hat;
import com.clothes.shop.repository.HatRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.HatService;

import lombok.experimental.PackagePrivate;

@RestController
@RequestMapping("hat")
public class HatController {
	
	@Autowired
	HatService hatService;
	
	@Autowired
	HatRepository hatRepository;
	
	@Autowired
	CategoryService categoryService;
	
	/*********************GET  HAT BY CATEGORY***************/
	
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<?> getHatByCategory(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		
		if(category == null) {
			return ResponseEntity.badRequest().body("Category is invalid");
		}
		
		List<HatDto> hatList = hatService.getAllHatByCategory(category);
		return ResponseEntity.ok().body(hatList);
	}
	
	/*********************GET ALL HAT**********************/
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<HatDto>> getAllHat(){
		return ResponseEntity.ok().body(hatService.getAllHat());
	}
	
	/*********************GET HAT BY ID**********************/
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<HatDto> getByHatId(@PathVariable int id){
		HatDto hatDto = hatService.getHat(id);
		if(hatDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(hatDto);
	}
	
	/*********************GET ALL HATTYPE**********************/
	
	@GetMapping(value = "/name")
	public ResponseEntity<List<Hat>> getAllByHatType(String hatType){
		return ResponseEntity.ok().body(hatService.getAllByHatType(hatType));
	}
	
	/*********************GET BY HATTYPE**********************/
	
	@GetMapping(value = "/hatType/{hatType}")
	public ResponseEntity<Boolean> getByHatType(@PathVariable("hatType")String hatType){
		Hat hat = hatService.getByHatType(hatType);
		if(hat == null) {
			return ResponseEntity.ok().body(false);
		}
		
		return ResponseEntity.ok().body(true);
	}

}
