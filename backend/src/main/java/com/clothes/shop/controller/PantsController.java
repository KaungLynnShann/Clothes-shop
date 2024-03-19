package com.clothes.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.dto.PantsDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Pants;
import com.clothes.shop.repository.PantsRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.PantsService;

@RestController
@RequestMapping("pants")
public class PantsController {
	
	@Autowired
	PantsRepository pantsRepository;
	
	@Autowired
	PantsService pantsService;
	
	@Autowired
	CategoryService categoryService;
	
	/*********************GET PANTS BY CATEGORY***************/
	
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<?> getPantsByCategory(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		
		if(category == null) {
			return ResponseEntity.badRequest().body("category is invalid");
		}
		List<PantsDto> pantsList = pantsService.getPantsByCategory(category);
		
		return ResponseEntity.ok().body(pantsList);
	}
	
	/*********************GET ALL PANTS**********************/
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<PantsDto>> getAllPants(){
		return ResponseEntity.ok().body(pantsService.getAllPants());
	}
	
	/*********************GET PANTS BY ID**********************/
	
	@GetMapping(value = ("/get/{id}"))
	public ResponseEntity<PantsDto> getByPantsId(@PathVariable int id){
		PantsDto pantsDto = pantsService.getPants(id);
		if(pantsDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(pantsDto);
	}
	
	/*********************GET ALL PANTSTYPE**********************/
	
	@GetMapping(value = "/name")
	public ResponseEntity<List<Pants>> getAllPantsType(String pantsType){
		return ResponseEntity.ok().body(pantsService.getAllByPantsType(pantsType));
	}	
	
	/*********************GET PANTSTYPE**********************/
	
	@GetMapping(value = "/pantsType/{pantsType}")
	public ResponseEntity<Boolean> getPantsType(@PathVariable("pantsType")String pantsType){
		Pants pants = pantsService.getByPantsType(pantsType);
		if(pants == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

}
