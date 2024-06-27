package com.nisum.demo.service;

import com.nisum.demo.model.User;

public interface UserService {
	
	User saveUser(User userSave);
	
	User findUserByEmail(String email);

}
