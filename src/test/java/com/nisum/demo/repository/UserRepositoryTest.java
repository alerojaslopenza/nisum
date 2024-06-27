package com.nisum.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nisum.demo.model.User;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	private User user;
	
	
	@BeforeEach
    public void setUp() {
		user = new User();
		user.setCreated(LocalDateTime.now());
		user.setEmail("test@mailinator.com");
		user.setIsActive(Boolean.TRUE);
		user.setToken("token");
		user.setPassword("12345");
		user.setName("test");
		userRepository.save(user);
	}
	
	 @Test
	 void shouldFindUserByEmail() {
        Optional<User> savedUser = userRepository.findByEmail("test@mailinator.com");
        assertNotNull(savedUser.get());
        assertEquals(user.getName(), savedUser.get().getName());
        
	  }
	 
	 
	 
}
