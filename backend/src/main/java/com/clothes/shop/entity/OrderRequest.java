package com.clothes.shop.entity;

import java.util.ArrayList;
import java.util.List;

import com.clothes.shop.enumType.RecordStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
	
	@JsonProperty("orderId")
	private int OrderId;
	
	@JsonProperty("orderItems")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private Customer customerData;
	
	private double orderTotal;
	
	private RecordStatus status;

}
