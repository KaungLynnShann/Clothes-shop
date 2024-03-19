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
//@Table(name = "boot")
public class Boot extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bootId;
	
	@NotBlank(message = "required")
	private String bootType;
	
	private String bootPhoto;
	
	@NotNull(message = "required")
	private double bootPrice;
	
	@NotNull(message = "required")
	@Min(value = 1,message = "size should be greater than 1")
	private int bootSize;
	
	@NotNull(message = "required")
	private int bootQuantity;
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "categoryId",nullable = false)
	private Category bootCategory;
}
