package com.clothes.shop.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.dto.BootDto;
import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Category;
import com.clothes.shop.repository.BootRepository;
import com.clothes.shop.service.BootService;
import com.clothes.shop.service.CategoryService;

@Service
public class BootServiceImpl implements BootService {
	
	@Autowired
	BootRepository bootRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Boot createBoot(BootDto bootDto) {
		return bootRepository.save(mapToEntity(bootDto));
	}

	@Override
	public List<BootDto> getAllBoot() {
		List<Boot> bootList = bootRepository.findAll();
		List<BootDto> bootListDto = bootList.stream().map
				(boot->mapToDto(boot)).collect(Collectors.toList());
		return bootListDto;
	}

	@Override
	public BootDto getBoot(int id) {
		Boot boot = bootRepository.findById(id).orElse(null);
		return mapToDto(boot);
	}

	@Override
	public Boot updateBoot(int id, BootDto bootDto) {
		BootDto updateBootDto = this.getBoot(id);
		Category category = categoryService.getCategory(
				bootDto.getBootCategory().getCategoryId());
		if(updateBootDto == null) {
			return null;
		}
		if (category == null) {
		   return null;
		}

		updateBootDto.setBootType(bootDto.getBootType());
		updateBootDto.setBootPhoto(bootDto.getBootPhoto());
		updateBootDto.setBootPrice(bootDto.getBootPrice());
		updateBootDto.setBootSize(bootDto.getBootSize());
		updateBootDto.setBootQuantity(bootDto.getBootQuantity());
		updateBootDto.setBootCategory(category);
		
		return bootRepository.save(mapToEntity(updateBootDto));
	}

	@Override
	public boolean deleteBoot(int id) {
		Boot boot = bootRepository.findById(id).orElse(null);
		if(boot == null) {
			return false;
		}
		bootRepository.deleteById(id);
		return true;
	}

	@Override
	public Boot getByBootType(String bootType) {
		return bootRepository.findByBootType(bootType);
	}

	@Override
	public List<Boot> getAllByBootType(String bootType) {
		return bootRepository.findAllByBootType(bootType);
	}
	
	@Override
	public List<BootDto> getAllBootByCategory(Category category) {
		List<BootDto> bootList = bootRepository.findByBootCategory(category).stream()
				.map(boot->mapToDto(boot)).collect(Collectors.toList());
		return bootList;
	}

	/*********CONVERT ENTITY TO DTO ***********/
	
	public BootDto mapToDto(Boot boot) {
		return modelMapper.map(boot, BootDto.class);
	}
	
	/*********CONVERT  DTO TO ENTITY***********/
	
	public Boot mapToEntity(BootDto bootDto) {
		return modelMapper.map(bootDto, Boot.class);
	}

}
