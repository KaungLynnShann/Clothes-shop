package com.clothes.shop.service;

import com.clothes.shop.entity.User;

public interface UserService {

	 User login(String userEmail, String userPassword);
}
