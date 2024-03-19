package com.clothes.shop.dto;

import java.util.List;

import com.clothes.shop.entity.BaseEntity;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.ToLowerCaseConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PantsDto extends BaseEntity {
	
	private int pantsId;
	
	@JsonDeserialize(converter = ToLowerCaseConverter.class)
	private String pantsType;
	
	private String pantsPhoto;
	
	private int pantsSize;
	
	private double pantsPrice;
	
	private int pantsQuantity;
	
	private Category pantsCategory;

}
