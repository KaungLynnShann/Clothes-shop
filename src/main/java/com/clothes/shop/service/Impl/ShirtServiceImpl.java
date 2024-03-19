package com.clothes.shop.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.dto.ShirtDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Shirt;
import com.clothes.shop.repository.ShirtRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.ShirtService;

@Service
public class ShirtServiceImpl implements ShirtService {
	
	@Autowired
	ShirtRepository shirtRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public Shirt createShirt(ShirtDto shirtDto) {
		return shirtRepository.save(mapToEntity(shirtDto));
	}

	@Override
	public List<ShirtDto> getAllShirt() {
		List<Shirt> shirtList=shirtRepository.findAll();
		List<ShirtDto> shirtListDto = shirtList.stream().map
				(shirt ->mapToDto(shirt)).collect(Collectors.toList());
		return shirtListDto;
	}

	@Override
	public ShirtDto getShirt(int id) {
		Shirt shirt= shirtRepository.findById(id).orElse(null);
		return mapToDto(shirt);
		
	}

	@Override
	public Shirt updateShirt(int id, ShirtDto shirtDto) {
		ShirtDto updateShirtDto= this.getShirt(id);
		Category category = categoryService.getCategory
				(shirtDto.getShirtCategory().getCategoryId());
		 
		if(updateShirtDto != null) {
			updateShirtDto.setShirtType(shirtDto.getShirtType());
			updateShirtDto.setShirtPhoto(shirtDto.getShirtPhoto());
			updateShirtDto.setShirtPrice(shirtDto.getShirtPrice());
			updateShirtDto.setShirtCategory(category);
			updateShirtDto.setShirtQuantity(shirtDto.getShirtQuantity());
			updateShirtDto.setShirtSize(shirtDto.getShirtSize());
		}
		
		
		return shirtRepository.save(mapToEntity(updateShirtDto));
	}

	@Override
	public Boolean deleteShirt(int id) {
		Shirt deleteShirt = shirtRepository.findById(id).orElse(null);
		if(deleteShirt == null) {
			return false; 
		}
		shirtRepository.deleteById(id);
		return true;
	}

	@Override
	public Shirt getByShirtType(String shirtType) {
		return shirtRepository.findByShirtType(shirtType);
	}
	@Override
	public List<Shirt> getAllByShirtType(String shirtType) {
		return shirtRepository.findAllByShirtType(shirtType);
	}
	
	@Override
	public List<ShirtDto> getAllShirtByCategory(Category category) {
		List<ShirtDto> shitList = shirtRepository.findByShirtCategory(category).stream()
				.map(shirt->mapToDto(shirt)).collect(Collectors.toList());
		return shitList;
	}
	
	/*********CONVERT  DTO TO ENTITY***********/
	public ShirtDto mapToDto(Shirt shirt) {
		return modelMapper.map(shirt,ShirtDto.class);
	}
	
	/*********CONVERT  ENTITY TO DTO ***********/
	public Shirt mapToEntity(ShirtDto shirtDto) {
		return modelMapper.map(shirtDto,Shirt.class);
	}

}
