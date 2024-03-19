package com.clothes.shop.service;

import java.util.List;

import com.clothes.shop.entity.Customer;
import com.clothes.shop.entity.Order;

public interface CustomerService {
 
	public Customer createCustomer(Customer customer);
	
//	public Customer saveCustomerAndOrder(Customer customer,List<Order> orders);
	
	public List<Customer> getAllCustomers();

	public Customer getCustomer(int id);

	public Customer updateCustomer(int id,String status);

	public Boolean deleteCustomer(int id);
	
//	public Customer updateCustomerStatus(int customerId);

}
