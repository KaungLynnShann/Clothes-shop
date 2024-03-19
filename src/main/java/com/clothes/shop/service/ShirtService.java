package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.dto.ShirtDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Shirt;

public interface ShirtService {
	
	Shirt createShirt(ShirtDto shirtDto);
	
	List<ShirtDto> getAllShirt();
	
	ShirtDto getShirt(int id);
	
	Shirt updateShirt(int id,ShirtDto shirtDto);
	
	Boolean deleteShirt(int id);
	
	public Shirt getByShirtType(String shirtType);

	public List<Shirt> getAllByShirtType(String shirtType);
	
	public List<ShirtDto> getAllShirtByCategory(Category category);
}
