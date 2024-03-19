package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.dto.HatDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Hat;

public interface HatService {
	
	Hat createHat(HatDto hatDto);
	
	List<HatDto> getAllHat();
	
	HatDto getHat(int id);
	
	Hat updateHat(int id,HatDto hatDto);
	
	boolean deleteHat(int id);
	
	public Hat getByHatType(String hatType);
	
	public List<Hat> getAllByHatType(String hatType);
	
	public List<HatDto> getAllHatByCategory(Category category);

}
