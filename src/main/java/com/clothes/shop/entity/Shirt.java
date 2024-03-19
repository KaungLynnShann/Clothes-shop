package com.clothes.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.clothes.shop.enumType.SizeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table(name="shirt")
public class Shirt extends BaseEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shirtId;
	
	
	@NotBlank(message = "Required")
	@Column(length = 50,nullable = false)
	private String shirtType;
	
	private String shirtPhoto;
	
	@NotNull(message = "Required")
	@Min(value = 1,message = "Price should greater than 1")
	private double shirtPrice;
	
	@NotNull(message = "Required") 
	@Min(value = 1,message = "quantity should greater than 1")
	private int shirtQuantity;
	
	@Column(columnDefinition = "ENUM('S','M','L','XL')NOT NULL")
	@Enumerated(EnumType.STRING)
	private SizeEnum shirtSize;
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "categoryId",nullable = false)
	private Category shirtCategory;
	
}
