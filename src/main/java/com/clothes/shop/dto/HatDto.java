package com.clothes.shop.dto;


import com.clothes.shop.entity.BaseEntity;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.ToLowerCaseConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HatDto extends BaseEntity {
	
	private int hatId;
	
	@JsonDeserialize(converter = ToLowerCaseConverter.class)
	private String hatType;
	
	private String hatPhoto;
	
	private double hatPrice;
	 
	private int hatQuantity;
	
	private Category hatCategory;
	
}
