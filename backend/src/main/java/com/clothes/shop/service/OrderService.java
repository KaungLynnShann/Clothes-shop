package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.entity.Order;
import com.clothes.shop.enumType.RecordStatus;

public interface OrderService {

	public Order createOrder(Order order);
	
	public List<Order> getAllOrders();
	
	public Order getOrder(int id);
	
	public Order updateOrder(int id,Order order);
	
	public Boolean deleteOrder(int id);
	
	public Order updateOrderStatus(int orderId,RecordStatus newStatus);
}
