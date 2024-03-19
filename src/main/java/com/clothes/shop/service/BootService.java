package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.dto.BootDto;
import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Category;

public interface BootService {
	
	Boot createBoot (BootDto bootDto);
	
	List<BootDto> getAllBoot();
	
	BootDto getBoot(int id);
	
	Boot updateBoot (int id,BootDto bootDto);
	
	boolean deleteBoot(int id);
	
	public Boot getByBootType(String bootType);
	
	public List<Boot> getAllByBootType(String bootType);
	
	public List<BootDto> getAllBootByCategory(Category category);

}
