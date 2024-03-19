package com.clothes.shop.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.dto.PantsDto;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Pants;
import com.clothes.shop.repository.PantsRepository;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.PantsService;

@Service
public class PantsServiceImpl implements PantsService {
	
	@Autowired
	PantsRepository pantsRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
    ModelMapper modelMapper;

	@Override
	public Pants createPants(PantsDto pantsDto) {
		return pantsRepository.save(mapToEntity(pantsDto));
	}

	@Override
	public List<PantsDto> getAllPants() {
		List<Pants> pantsList=pantsRepository.findAll();
		List<PantsDto> pantsListDto = pantsList.stream().map
				(pants->mapToDto(pants)).collect(Collectors.toList());
		return pantsListDto;
	}

	@Override
	public PantsDto getPants(int id) {
		Pants pants = pantsRepository.findById(id).orElse(null);
		return mapToDto(pants);
	}

	@Override
	public Pants updatePants(int id, PantsDto pantsDto) {
		PantsDto updatePantsDto = this.getPants(id);
		Category category = categoryService.getCategory(
				pantsDto.getPantsCategory().getCategoryId());
		
		if(updatePantsDto == null) {
			return null;
		}
		
		updatePantsDto.setPantsType(pantsDto.getPantsType());
		updatePantsDto.setPantsPhoto(pantsDto.getPantsPhoto());
		updatePantsDto.setPantsSize(pantsDto.getPantsSize());
		updatePantsDto.setPantsPrice(pantsDto.getPantsPrice());
		updatePantsDto.setPantsQuantity(pantsDto.getPantsQuantity());
		updatePantsDto.setPantsCategory(category);
		
		return pantsRepository.save(mapToEntity(updatePantsDto));
	}
	
	@Override
	public Boolean deletePants(int id) {
		Pants pants = pantsRepository.findById(id).orElse(null);
		if(pants == null) {
			return false;
		}
		pantsRepository.deleteById(id);
		return true;
	}

	@Override
	public Pants getByPantsType(String pantsType) {
		return pantsRepository.findByPantsType(pantsType);
	}

	@Override
	public List<Pants> getAllByPantsType(String pantsType) {
		return pantsRepository.findAllByPantsType(pantsType);
	}
	
	@Override
	public List<PantsDto> getPantsByCategory(Category category) {
		List<PantsDto> pantsList = pantsRepository.findByPantsCategory(category).stream()
				.map(pants->mapToDto(pants)).collect(Collectors.toList());
		return pantsList;
	}
	
	/*********CONVERT  DTO TO ENTITY***********/
	
	public PantsDto mapToDto(Pants pants) {
		return modelMapper.map(pants,PantsDto.class);
	}
	
	/*********CONVERT ENTITY TO DTO  ***********/
	
	public Pants mapToEntity(PantsDto pantsDto) {
		return modelMapper.map(pantsDto,Pants.class);
	}

}
