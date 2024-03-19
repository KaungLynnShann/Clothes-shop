package com.clothes.shop;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.clothes.shop.entity.User;
import com.clothes.shop.repository.UserRepository;
import com.clothes.shop.service.StorageService;


@SpringBootApplication
public class ShopApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	StorageService storageService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlMode;

	@Value("${custom.delete.files}")
	private String deleteFiles;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (ddlMode.equals("create")) {
			
			//Create Admin 
			User adminUser = new User();
			adminUser.setUserId(1);
			adminUser.setUserName("admin");
			adminUser.setUserPassword(bCryptPasswordEncoder.encode("admin"));
			adminUser.setUserEmail("admin@gmail.com");
			userRepository.save(adminUser); 
//           
//			userService.create(
//					new User(1,"admin", bCryptPasswordEncoder.encode("admin") ,"admin@gmail.com")
//					);
		}
		if (deleteFiles.equals("true")) {
			storageService.clearAll();
		}
		
	}


}
