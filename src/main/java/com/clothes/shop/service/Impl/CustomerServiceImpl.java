package com.clothes.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.shop.entity.Customer;
import com.clothes.shop.entity.Order;
import com.clothes.shop.entity.OrderItem;
import com.clothes.shop.enumType.RecordStatus;
import com.clothes.shop.repository.CustomerRepository;
import com.clothes.shop.service.BootService;
import com.clothes.shop.service.HatService;
import com.clothes.shop.service.OrderService;
import com.clothes.shop.service.CustomerService;
import com.clothes.shop.service.PantsService;
import com.clothes.shop.service.ShirtService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderService orderService;
	

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(int id) {
		return customerRepository.findById(id).orElseThrow();
	}

//	@Override
//	public Customer updateCustomer(int id, String status) {
//		Customer updateCustomer = this.getCustomer(id);
//		updateCustomer.setStatus(RecordStatus.CONFIRM);
//		
//		customerRepository.save(updateCustomer);
//		
//		return updateCustomer;
//	}

	@Override
	public Boolean deleteCustomer(int id) {
		Customer customer = this.getCustomer(id);
		if(customer == null) {
			return false;
		}
		customerRepository.deleteById(id);
		return true;
	}

	@Override
	public Customer updateCustomer(int id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Customer> findByCustomerStatus(String status) {
//		List<Customer> statusList = customerRepository.findByStatus(RecordStatus.PROCESSING);
//		return statusList;
//	}

//	@Override
//	public Customer updateCustomerStatus(int customerId) {
//		Customer customer = customerRepository.findById(customerId).orElse(null);
//		customer.setStatus(RecordStatus.CONFIRM);
//		
//		Customer updateCustomer = customerRepository.save(customer);
//		return updateCustomer;
//	}

//	@Override
//	public Customer saveCustomerAndOrder(Customer customer, List<Order> orders) {
//	    Customer savedCustomer = customerRepository.save(customer);
//	    
//	    for (Order orderItem : orders) {
//	        Order order = new Order();
//	        order.setCustomerData(savedCustomer);
//	        
//	        OrderItem newItem = new OrderItem(); 
//	        newItem.setOrder(order);
//	        order.getOrderItems().add(newItem);
//	        
//	        orderService.createOrder(order);
//	    }
//
//	    return savedCustomer;
//	}

}
