package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.dto.PantsDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Pants;

public interface PantsService {
	
	Pants createPants(PantsDto pantsDto);
	
	List<PantsDto> getAllPants();
	
	PantsDto getPants(int id);
	
	Pants updatePants(int id,PantsDto pantsDto);
	
	Boolean deletePants(int id);
	
	public Pants getByPantsType(String pantsType);
	
	public List<Pants> getAllByPantsType(String pantsType);
	
	public List<PantsDto> getPantsByCategory(Category category);

}
