package com.clothes.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.shop.entity.LoginRequest;
import com.clothes.shop.entity.User;
import com.clothes.shop.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	@PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody Map<String, String> loginRequest)  {
//		String email = loginRequest.get("userEmail");
//		
//	    String password = loginRequest.get("userPassword");
//
//	    try {
//	        User user = userService.login(email, password);
//
//	        if (user != null) {
//	            return ResponseEntity.ok("Login successful");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
//	        }
//	    } catch (AuthenticationException e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
//	    }
//    }
	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody LoginRequest loginRequest) {
		
	    User user = userService.
	    		login(loginRequest.getUserEmail(),loginRequest.getUserPassword());

		if (user != null) {
		    return ResponseEntity.ok().body(user);
		} else {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
