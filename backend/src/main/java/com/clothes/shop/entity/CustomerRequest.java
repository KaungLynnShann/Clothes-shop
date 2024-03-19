package com.clothes.shop.entity;

import java.util.List;

import com.clothes.shop.enumType.RecordStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

	private int customerId;
	
	private String name;
	
	private int phNo;
	
	private String address;
	
	private String city;
	
	private RecordStatus status;
	
	 private List<Order> orders;
	 
	 private Customer customer;
}
