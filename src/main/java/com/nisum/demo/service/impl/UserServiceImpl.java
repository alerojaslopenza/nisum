package com.nisum.demo.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.demo.exception.EmailExistException;
import com.nisum.demo.model.User;
import com.nisum.demo.repository.UserRepository;
import com.nisum.demo.service.JwtService;
import com.nisum.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public User saveUser(User userSave) {
		User userSaved=null;
		if(findUserByEmail(userSave.getEmail())!=null) {
			LOGGER.error("User Exist {}" , userSave.getEmail());
			throw  new EmailExistException(EmailExistException.EMAIL_EXIST);
		}
		
		String token = jwtService.generateToken(userSave);
		userSave.setToken(token);
		userSave.setIsActive(Boolean.TRUE);
		userSaved = userRepository.save(userSave);
		return userSaved;
	}
	
	public User findUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		return userOptional.orElse(null);
	}

}
