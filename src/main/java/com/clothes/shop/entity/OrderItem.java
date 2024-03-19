package com.clothes.shop.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem extends BaseEntity implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	
	@ManyToOne
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "shirt_id",referencedColumnName = "shirtId")
	private Shirt shirt;
	
	@ManyToOne
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "pants_id",referencedColumnName = "pantsId")
	private Pants pants;
	
	@ManyToOne
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "boot_id",referencedColumnName = "bootId")
	private Boot boot;
	
	@ManyToOne
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "hat_id",referencedColumnName = "hatId")
	private Hat hat;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "order_id",referencedColumnName = "orderId")
	private Order order;
	
	private double amount;
	private int quantity;
}
