package com.clothes.shop.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.entity.OrderItem;
import com.clothes.shop.repository.OrderItemRepository;
import com.clothes.shop.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	@Override
	public Boolean deleteOrderItem(int id) {
		OrderItem orderItem = this.getOrderItem(id);
		if(orderItem == null) {
			return false;
		}
		orderItemRepository.deleteById(id);
		return true;
	}

	@Override
	public OrderItem getOrderItem(int id) {
		return orderItemRepository.findById(id).orElse(null);
	}

}
