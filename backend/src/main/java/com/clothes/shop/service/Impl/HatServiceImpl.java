package com.clothes.shop.service.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.dto.HatDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Hat;
import com.clothes.shop.repository.HatRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.HatService;

@Service
public class HatServiceImpl implements HatService {
	
	@Autowired
	HatRepository hatRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Hat createHat(HatDto hatDto) {
		return hatRepository.save(mapToEntity(hatDto));
	}

	@Override
	public List<HatDto> getAllHat() {
		List<Hat> hatList = hatRepository.findAll();
		List<HatDto> hatListDto = hatList.stream().map
				(hat ->mapToDto(hat)).collect(Collectors.toList());
		return hatListDto;
	}

	@Override
	public HatDto getHat(int id) {
		Hat hat = hatRepository.findById(id).orElse(null);
		return mapToDto(hat);
	}

	@Override
	public Hat updateHat(int id, HatDto hatDto) {
		HatDto updateHatDto = this.getHat(id);
		Category category = categoryService.getCategory(
				hatDto.getHatCategory().getCategoryId());
		
		updateHatDto.setHatType(hatDto.getHatType());
		updateHatDto.setHatPhoto(hatDto.getHatPhoto());
		updateHatDto.setHatPrice(hatDto.getHatPrice());
		updateHatDto.setHatQuantity(hatDto.getHatQuantity());
		updateHatDto.setHatCategory(category);
		
		return hatRepository.save(mapToEntity(updateHatDto));
	}

	@Override
	public boolean deleteHat(int id) {
		Hat hat = hatRepository.findById(id).orElse(null);
		if(hat == null) {
			return false;
		}
		hatRepository.deleteById(id);
		return true;
	}

	@Override
	public Hat getByHatType(String hatType) {
		return hatRepository.findByHatType(hatType);
	}

	@Override
	public List<Hat> getAllByHatType(String hatType) {
		return hatRepository.findByAllHatType(hatType);
	}
	
	@Override
	public List<HatDto> getAllHatByCategory(Category category) {
		List<HatDto> hatList = hatRepository.findByHatCategory(category).stream()
				.map(hat->mapToDto(hat)).collect(Collectors.toList());
		return hatList;
	}

	
	/*********CONVERT  DTO TO ENTITY***********/
	
	public HatDto mapToDto(Hat hat) {
		return modelMapper.map(hat, HatDto.class);
	}
	
	/*********CONVERT ENTITY TO  DTO ***********/
	
	public Hat mapToEntity(HatDto hatDto) {
		return modelMapper.map(hatDto, Hat.class);
	}

}
