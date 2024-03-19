package com.clothes.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clothes.shop.entity.User;
import com.clothes.shop.repository.UserRepository;
import com.clothes.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User login(String userEmail, String userPassword) {
//		 Optional<User> userOptional = userRepository.findByUserEmail(userEmail);
	        
//	        if (userOptional.isPresent()) {
//            User user = userOptional.get();
	     
//	            if (userPassword.equals(user.getUserPassword())) {
//	                return user;
//	            }
//	        }
//	        return null;
		User user = userRepository.findByUserEmail(userEmail);
		
		if(user == null) {
			return null;
		}
		
		if(!bCryptPasswordEncoder.matches(userPassword, user.getUserPassword())) {
			return null;
		}
		
		return userRepository.save(user);
	    }
	
  }
