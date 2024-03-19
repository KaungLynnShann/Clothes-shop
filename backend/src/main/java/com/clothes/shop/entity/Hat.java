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
//@Table(name = "hat")
public class Hat extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hatId;
	
	@NotBlank(message = "Required")
	private String hatType;
	
	@NotBlank(message = "Required")
	private String hatPhoto;
	
	@NotNull(message = "Required")
	private double hatPrice;
	
	@NotNull(message = "Required")
	private int hatQuantity;	
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "categoryId",nullable = false)
	private Category hatCategory;
}
