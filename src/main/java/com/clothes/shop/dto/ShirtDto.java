package com.clothes.shop.dto;



import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.clothes.shop.entity.BaseEntity;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.ToLowerCaseConverter;
import com.clothes.shop.enumType.SizeEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShirtDto extends BaseEntity{

	private int shirtId;
	
	@JsonDeserialize(converter = ToLowerCaseConverter.class)
	private String shirtType;
	
	private String shirtPhoto;
	
	private double shirtPrice;
	
	private Category shirtCategory;
	
	private int shirtQuantity;
	
	@Enumerated(EnumType.STRING)
	private SizeEnum shirtSize;
	
}
