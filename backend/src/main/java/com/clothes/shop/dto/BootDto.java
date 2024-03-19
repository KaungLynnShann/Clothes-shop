package com.clothes.shop.dto;

import com.clothes.shop.entity.BaseEntity;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.ToLowerCaseConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BootDto extends BaseEntity {
	
	private int bootId;
	
	@JsonDeserialize(converter = ToLowerCaseConverter.class)
	private String bootType;
	
	private String bootPhoto;
	
	private double bootPrice;
	
	private int bootSize;
	
	private int bootQuantity;
	
	private Category bootCategory;

}
