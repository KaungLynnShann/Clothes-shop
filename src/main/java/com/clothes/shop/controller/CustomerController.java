package com.clothes.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.entity.Customer;
import com.clothes.shop.service.CustomerService;
import com.clothes.shop.service.OrderService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderServie;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Customer> createOrder(@Valid @RequestBody Customer order){
		return ResponseEntity.ok().body(customerService.createCustomer(order));
	}
}
