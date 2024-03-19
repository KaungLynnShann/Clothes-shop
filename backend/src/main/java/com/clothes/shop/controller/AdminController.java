package com.clothes.shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.clothes.shop.dto.BootDto;
import com.clothes.shop.dto.HatDto;
import com.clothes.shop.dto.PantsDto;
import com.clothes.shop.dto.ShirtDto;
import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Hat;
import com.clothes.shop.entity.Order;
import com.clothes.shop.entity.OrderItem;
import com.clothes.shop.entity.Customer;
import com.clothes.shop.entity.Pants;
import com.clothes.shop.entity.Shirt;
import com.clothes.shop.enumType.RecordStatus;
import com.clothes.shop.enumType.SizeEnum;
import com.clothes.shop.service.BootService;
import com.clothes.shop.service.CategoryService;
import com.clothes.shop.service.HatService;
import com.clothes.shop.service.OrderItemService;
import com.clothes.shop.service.OrderService;
import com.clothes.shop.service.CustomerService;
import com.clothes.shop.service.PantsService;
import com.clothes.shop.service.ShirtService;
import com.clothes.shop.service.StorageService;

import lombok.val;
import lombok.experimental.PackagePrivate;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	ShirtService shirtService;
	
	@Autowired
	PantsService pantsService;
	
	@Autowired
	HatService hatService;
	
	@Autowired
	BootService bootService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	
	/*
	 * ----------------------------------- START BY SHIRT
	 * -----------------------------------
	 */
	/************************CREATE SHIRT***********************/
	
	@PostMapping(value = "/shirt/create")
	public ResponseEntity<Shirt>createShirt(@Valid @RequestBody ShirtDto shirtDto){
		return ResponseEntity.ok().body(shirtService.createShirt(shirtDto));
	}
	/************************UPDATE SHIRT***********************/
	
	@PutMapping(value = "/shirt/update/{id}")
	public ResponseEntity<Shirt>updateShirt(@PathVariable int id,
			@Valid @RequestBody ShirtDto shirtDto){
		Shirt updateShirt = shirtService.updateShirt(id, shirtDto);
		if(updateShirt == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updateShirt);
	}
	/************************DELETE SHIRT***********************/
	
	@DeleteMapping(value = "/shirt/delete/{id}")
	public ResponseEntity<?> deleteShirt(@PathVariable int id){
			ShirtDto shirtDto = shirtService.getShirt(id);
			
			if(shirtDto == null) {
				return ResponseEntity.notFound().build();
			}
			boolean isDelete = shirtService.deleteShirt(id);
			
			if(!isDelete) {
				return ResponseEntity.notFound().build();
			}
			
//			Delete photo
			storageService.delete(shirtDto.getShirtPhoto());
			
			return ResponseEntity.ok().build();
		}
	
	/*******************************************************************************/
	
	/*
	 * ----------------------------------- START BY PANTS
	 * -----------------------------------
	 */
	/************************CREATE PANTS***********************/
	
	@PostMapping(value = "/pants/create")
	public ResponseEntity<Pants> createPants(@Valid@RequestBody PantsDto pantsDto){
		return ResponseEntity.ok().body(pantsService.createPants(pantsDto));
	}
	
	/************************UPDATE PANTS***********************/
	
	@PutMapping(value = "/pants/update/{id}")
	public ResponseEntity<Pants> updatePants(@PathVariable int id,
			@Valid@RequestBody PantsDto pantsDto){
		Pants updatePants = pantsService.updatePants(id, pantsDto);
		if(updatePants == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatePants);
	}
	
	/************************DELETE PANTS***********************/
	
	@DeleteMapping(value = "/pants/delete/{id}")
	public ResponseEntity<?> deletePants(@PathVariable int id){
		PantsDto pantsDto = pantsService.getPants(id);
		if(pantsDto == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDeleted = pantsService.deletePants(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
//		Delete photo
		storageService.delete(pantsDto.getPantsPhoto());
		
		return ResponseEntity.ok().build();
	}
	
	/*******************************************************************************/
	
	/*
	 * ----------------------------------- START BY HAT
	 * -----------------------------------
	 */
	/************************CREATE PANTS***********************/
	
	@PostMapping(value = "/hat/create")
	public ResponseEntity<Hat> createHat(@Valid @RequestBody HatDto hatDto){
		return ResponseEntity.ok().body(hatService.createHat(hatDto));
	}
	
	/************************UPDATE HAT***********************/
	
	@PutMapping(value = "/hat/update/{id}")
	public ResponseEntity<Hat> updateHat(@PathVariable int id,
			@Valid @RequestBody HatDto hatDto){
		Hat updateHat = hatService.updateHat(id, hatDto);
		if(updateHat == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updateHat);
	}
	
	/************************DELETE HAT***********************/
	
	@DeleteMapping(value = "/hat/delete/{id}")
	public ResponseEntity<?> deleteHat(@PathVariable int id){
		HatDto hatDto = hatService.getHat(id);
		if(hatDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		boolean isDeleted = hatService.deleteHat(id);
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		storageService.delete(hatDto.getHatPhoto());
		
		return ResponseEntity.ok().build();
	}
	
	/*******************************************************************************/
	
	/*
	 * ----------------------------------- START BY BOOT
	 * -----------------------------------
	 */
	/************************CREATE BOOT***********************/
	
	@PostMapping(value = "/boot/create")
	public ResponseEntity<Boot> createBoot(@Valid @RequestBody BootDto bootDto){
		return ResponseEntity.ok().body(bootService.createBoot(bootDto));
	}
	
	/************************UPDATE BOOT***********************/
	
	@PutMapping(value = "/boot/update/{id}")
	public ResponseEntity<Boot> updateBoot(@PathVariable int id,
			@Valid@RequestBody BootDto bootDto){
		Boot updateBoot = bootService.updateBoot(id, bootDto);
		if(updateBoot == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updateBoot);
	}
	
	/************************DELETE BOOT***********************/
	
	@DeleteMapping(value = "/boot/delete/{id}")
	public ResponseEntity<?> deleteBoot(@PathVariable int id){
		BootDto bootDto = bootService.getBoot(id);
		if(bootDto == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDeleted = bootService.deleteBoot(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		storageService.delete(bootDto.getBootPhoto());
		
		return ResponseEntity.ok().build();
	}
	/*******************************************************************************/
	
	/*
	 * ----------------------------------- START BY CATEGORY
	 * -----------------------------------
	 */
	/************************CREATE CATEGORY***********************/
	
	@PostMapping(value = "/category/create")
	public ResponseEntity<Category> cretaeCategory(@Valid@RequestBody Category category){
		Category newCategory = categoryService.createCategory(category);
		return new ResponseEntity<Category>(newCategory,HttpStatus.OK);
	}
	
	/************************UPDATE CATEGORY***********************/
	
	@PutMapping(value = "/category/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id,
			@Valid @RequestBody Category category){
		Category updateCategory = categoryService.updateCategory(id, category);
		if(updateCategory == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updateCategory);
	}
	
	/************************DELETE CATEGORY***********************/
	
	@DeleteMapping(value = "/catgory/delete/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable int id){
		Category category = categoryService.getCategory(id);
		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDeleted = categoryService.deleteCategory(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
   /*******************************************************************************/
     /**************************************************************************/
	
	/*
	 * ----------------------------------- START BY CUSTOMER
	 * -----------------------------------
	 */
	
	/************************UPDATE CUSTOMER***********************/
	
//	@PutMapping(value = "/customer/update/{id}")
//	public ResponseEntity<Customer> updateCustomer(@PathVariable int id,
//			@Valid @RequestBody String status){
//		Customer updateStatus = customerService.updateCustomer(id, status);
//		
//		if(updateStatus == null) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		return new ResponseEntity<Customer>(updateStatus,HttpStatus.OK);
//	}
	
	 
	/************************all customer***********************/
	
	@GetMapping(value = "/customer/list")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		return ResponseEntity.ok().body(customerService.getAllCustomers());

	}
	
	@GetMapping(value = "/get/customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);

	}
	
	/************************DELETE customer***********************/
	
	@DeleteMapping(value = "/customer/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id){
		Customer customer = customerService.getCustomer(id);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDeleted = customerService.deleteCustomer(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	 /*******************************************************************************/
    /**************************************************************************/
	
	/*
	 * ----------------------------------- START BY ORDER
	 * -----------------------------------
	 */
	
	/************************GET status***********************/
	@GetMapping(value = "/status/list")
	public RecordStatus[] getStatus() {
		return RecordStatus.values();
	}
	
/************************UPDATE STATUS***********************/
	
	@PutMapping(value = "/update/order_status/{orderId}")
	public ResponseEntity<Order>updateOrderStatus(@PathVariable("orderId") int orderId,
			@Valid@RequestBody Order order) {
		
		 Order updatedOrder = orderService.updateOrderStatus(order.getOrderId(), 
				 order.getStatus());

		 if (updatedOrder != null) {
		        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	}
	/***** GET ALL ORDER LIST ******************************/
	
	@GetMapping(value = "/order/list")
	public ResponseEntity<List<Order>> getAllOrders(){
		Order order = new Order();
		List<Order> n = orderService.getAllOrders();
		System.out.println(n.size());
		return ResponseEntity.ok().body(n);
	}
	
	@GetMapping(value = "/get/order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable int id){
		Order order = orderService.getOrder(id);
		return ResponseEntity.ok().body(order);
	}
	/***** DELETE ORDER  ******************************/
	
	@DeleteMapping(value = "/order/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable int id){
		Order order = orderService.getOrder(id);
		if(order == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDelete = orderService.deleteOrder(id);
		if(!isDelete) {
			return ResponseEntity.notFound().build();
		}
		else return ResponseEntity.ok().build();
	}
	/***** DELETE ORDERITEM  ******************************/
	
	@DeleteMapping(value = "delete/order_item/{id}")
	public ResponseEntity<?> deleteOrderItem(@PathVariable int id){
		OrderItem orderItem = orderItemService.getOrderItem(id);
		
		if(orderItem == null) {
			return ResponseEntity.notFound().build();
		}
		boolean isDelete = orderItemService.deleteOrderItem(id);
		
		if(!isDelete) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	/*
	 * ----------------------------------- START BY FILE
	 * -----------------------------------
	 */
	/***** CreateFile ******************************/
	@PostMapping("/file/create")
	public ResponseEntity<String> createFile(@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType) {
		String filePath = storageService.create(file, fileType);
		if (filePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(filePath);
	}

	/***** UpdateFile ******************************/
	@PutMapping("/file/update")
	public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType, @RequestParam("filePath") String filePath) {
		String newFilePath = storageService.update(file, fileType, filePath);
		if (newFilePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(newFilePath);
	}
	/*
	 * ----------------------------------- END BY FILE
	 * -----------------------------------
	 */
}
