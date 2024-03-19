package com.clothes.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Pants")
public class Pants extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pantsId;
	
	@NotBlank(message = "required")
	private String pantsType;
	
	@NotBlank(message = "required")
	private String pantsPhoto;
	
	@NotNull(message = "required")
	private int pantsSize;
	
	@NotNull(message = "required")
	@Min(value = 1,message = "Price should greater than 1")
	private double pantsPrice;
	
	@NotNull(message = "required")
	private int pantsQuantity;
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "categoryId",nullable = false)
	private Category pantsCategory;

}
