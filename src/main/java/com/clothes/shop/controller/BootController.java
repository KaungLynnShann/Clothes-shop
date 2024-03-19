package com.clothes.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.dto.BootDto;
import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Category;
import com.clothes.shop.repository.BootRepository;
import com.clothes.shop.service.BootService;
import com.clothes.shop.service.CategoryService;

@RestController
@RequestMapping("boot")
public class BootController{

	@Autowired
	BootRepository bootRepository;
	
	@Autowired
	BootService bootService;
	
	@Autowired
	CategoryService categoryService;
	
	/*********************GET BOOT CATEGORY**********************/
	
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<?> getBootByCategory(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		
		if(category == null) {
			return ResponseEntity.badRequest().body("Category is invalid");
		}
		
		List<BootDto> bootList = bootService.getAllBootByCategory(category);
		return ResponseEntity.ok().body(bootList);
	}
	
	/*********************GET ALL BOOT**********************/
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<BootDto>> getAllBoot(){
		return ResponseEntity.ok().body(bootService.getAllBoot());
	}
	
	/*********************GET BOOT BY ID**********************/
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<BootDto> getByBootId(@PathVariable int id){
		BootDto bootDto = bootService.getBoot(id);
		if(bootDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bootDto);
	}
	
	/*********************GET ALL BOOTTYPE**********************/
	
	@GetMapping(value = "/name")
	public ResponseEntity<List<Boot>> getAllByBootType(String bootType){
		return ResponseEntity.ok().body(bootService.getAllByBootType(bootType));
	}
	
	/*********************GET BY BOOTTYPE**********************/
	
	public ResponseEntity<Boolean> getByBootType(@PathVariable("bootType")String bootType){
		Boot boot = bootService.getByBootType(bootType);
		
		if(boot == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}
}
