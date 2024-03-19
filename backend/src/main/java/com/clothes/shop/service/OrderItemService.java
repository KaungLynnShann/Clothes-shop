package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.entity.OrderItem;

public interface OrderItemService {
	
	public OrderItem createOrderItem(OrderItem orderItem);
//	public void createOrderItem(List<OrderItem> orderItems);
	public Boolean deleteOrderItem(int id);
	public OrderItem getOrderItem(int id);

}
