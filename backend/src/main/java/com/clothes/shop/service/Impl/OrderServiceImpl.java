package com.clothes.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.entity.Order;
import com.clothes.shop.enumType.RecordStatus;
import com.clothes.shop.repository.OrderRepository;
import com.clothes.shop.service.BootService;
import com.clothes.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrder(int id) {
		return orderRepository.findById(id).orElseThrow();
	}

	@Override
	public Order updateOrder(int id, Order order) {
		Order orderToEdit = this.getOrder(id);
		orderToEdit.setCustomerData(order.getCustomerData());
		orderToEdit.setOrderItems(order.getOrderItems());
		orderToEdit.setOrderTotal(order.getOrderTotal());
		return orderRepository.save(orderToEdit);
	}

	@Override
	public Boolean deleteOrder(int id) {
		Order order = orderRepository.findById(id).orElse(null);
		if(order == null) {
			return false;
		}
		orderRepository.deleteById(id);
		return true;
	}
	
	@Override
	public Order updateOrderStatus(int orderId,RecordStatus newStatus)  {
	    Order order = orderRepository.findById(orderId).orElse(null);

	    if (order == null) {
	    	return null;
	    }
	    order.setStatus(newStatus);
	    orderRepository.save(order);
	    return order;
	}

//	private RecordStatus convertStringToRecordStatus(String status) {
//		
//	    switch (status) {
//	        case "CONFIRM":
//	            return RecordStatus.CONFIRM;
//	        case "ORDER": 
//	            return RecordStatus.ORDER;
//	        default:
//	            throw new IllegalArgumentException("Invalid status: " + status);
//	    }
//	}
}
