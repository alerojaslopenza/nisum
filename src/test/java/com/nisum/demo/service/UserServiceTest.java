package com.nisum.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nisum.demo.exception.EmailExistException;
import com.nisum.demo.model.User;
import com.nisum.demo.repository.UserRepository;


@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@MockBean
	private JwtService jwtService;
	
	private User user;
	
	//http://localhost:8080/swagger-ui/index.html#/User/saveUser
	
	@BeforeEach
	public void init() {
		user = new User();
		user.setCreated(LocalDateTime.now());
		user.setEmail("test@mailinator.com");
		user.setIsActive(Boolean.TRUE);
		user.setToken("token");
		user.setPassword("12345");
		user.setName("test");
		userRepository.save(user);
	}
	
	@AfterEach
	public void down() {
		userRepository.deleteAll();
	}
	
	@Test
	public void shouldFindByEmail() {
		
		User userFound = userService.findUserByEmail(user.getEmail());
		
		assertNotNull(userFound);
		Assertions.assertEquals(userFound.getName(), user.getName());
		Assertions.assertEquals(userFound.getIsActive(), user.getIsActive());
		Assertions.assertEquals(userFound.getToken(), user.getToken());
	}

	@Test
	public void shouldNotFoundByEmail() {
		User userFound = userService.findUserByEmail("test1");
		assertNull(userFound);
	}
	
	@Test
	public void shouldSaveUser() {
		User user = new User();
		user.setCreated(LocalDateTime.now());
		user.setEmail("test1@mailinator.com");
		user.setIsActive(Boolean.TRUE);
		user.setPassword("98760");
		user.setName("testAdded");
		
		when(jwtService.generateToken(user)).thenReturn("tokenMock");
		
		User userAdded = userService.saveUser(user);
		assertNotNull(userAdded);
		Assertions.assertEquals(userAdded.getToken(), "tokenMock");
		
	}
	
	@Test
	public void shouldExceptionUserExist() {
		User user = new User();
		user.setEmail("test@mailinator.com");
		
		try {
			userService.saveUser(user);
			fail();
		} catch (EmailExistException e) {
			assertEquals("El correo ya registrado", e.getMessage());
		}
		
		
	}


}
