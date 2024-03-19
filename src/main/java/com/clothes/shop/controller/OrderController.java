package com.clothes.shop.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Hat;
import com.clothes.shop.entity.Order;
import com.clothes.shop.entity.OrderItem;
import com.clothes.shop.entity.OrderRequest;
import com.clothes.shop.entity.Pants;
import com.clothes.shop.entity.Shirt;
import com.clothes.shop.enumType.RecordStatus;
import com.clothes.shop.repository.BootRepository;
import com.clothes.shop.repository.HatRepository;
import com.clothes.shop.repository.OrderRepository;
import com.clothes.shop.repository.PantsRepository;
import com.clothes.shop.repository.ShirtRepository;
import com.clothes.shop.service.OrderItemService;
import com.clothes.shop.service.OrderService;
@RestController
@RequestMapping("order")
@Transactional
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BootRepository bootRepository;
	
	@Autowired
	ShirtRepository shirtRepository;
	
	@Autowired
	PantsRepository pantsRepository;
	
	@Autowired
	HatRepository hatRepository;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
	    try {
	        Order requestOrder = new Order();
	        requestOrder.setCustomerData(orderRequest.getCustomerData());
	        requestOrder.setOrderTotal(orderRequest.getOrderTotal());
	        requestOrder.setStatus(RecordStatus.ORDER);
	        
	        Order savedOrder = orderService.createOrder(requestOrder);
	        
	        for (int i =0;i < orderRequest.getOrderItems().size();i++) {
	            Shirt shirt = orderRequest.getOrderItems().get(i).getShirt();
	            Pants pants = orderRequest.getOrderItems().get(i).getPants();
	            Boot boot = orderRequest.getOrderItems().get(i).getBoot();
	            Hat hat = orderRequest.getOrderItems().get(i).getHat();

	            // Create a new order item and set its properties
	            OrderItem newOrderItem = new OrderItem();
	            newOrderItem.setQuantity(orderRequest.getOrderItems().get(i).getQuantity());
	            newOrderItem.setShirt(shirt);
	            newOrderItem.setPants(pants);
	            newOrderItem.setBoot(boot);
	            newOrderItem.setHat(hat);
	            newOrderItem.setOrder(savedOrder);
	            
	            // Calculate and set the total amount for the order item
	            double totalAmount = 0.0;
	            if (shirt != null) {
	                totalAmount += shirt.getShirtPrice();
	                shirt.getShirtId();
	            }
	            if (pants != null) {
	                totalAmount += pants.getPantsPrice();
	            }
	            if (boot != null) {
	                totalAmount += boot.getBootPrice();
	            }
	            if (hat != null) {
	                totalAmount += hat.getHatPrice();
	            }
	            newOrderItem.setAmount(totalAmount);
	            
	            // Save the order item
	            orderItemService.createOrderItem(newOrderItem);
	        }
	        
	        return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}

//	@PostMapping("/create")
//	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
//	    Order requestOrder = new Order();    
//	    requestOrder.setCustomerData(orderRequest.getCustomerData());
//	    requestOrder.setOrderTotal(orderRequest.getOrderTotal());    
//	    Order newOrder = orderService.createOrder(requestOrder);
//	    
//	    for (OrderItem item : orderRequest.getOrderItems()) {
//	        // Assuming these entities are already persisted in the database,
//	        // otherwise, save them before creating the order item
//	        Shirt shirt = item.getShirt();
//	        Pants pants = item.getPants();
//	        Boot boot = item.getBoot();
//	        Hat hat = item.getHat();
//
//	        // Set the associated entities on the order item
//	        OrderItem orderItem = new OrderItem();
//	        orderItem.setOrder(newOrder);
//	        orderItem.setShirt(shirt);
//	        orderItem.setPants(pants);
//	        orderItem.setBoot(boot);
//	        orderItem.setHat(hat);
//	        orderItem.setQuantity(item.getQuantity());
//	        
//	        // Save the order item
//	        orderItemService.createOrderItem(orderItem);
//	    }
//	    
//	    return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
//	}
