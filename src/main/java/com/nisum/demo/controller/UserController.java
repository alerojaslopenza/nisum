package com.nisum.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.demo.dto.UserDTO;
import com.nisum.demo.model.Phone;
import com.nisum.demo.model.User;
import com.nisum.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User", description = "User Api")
@RestController
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;
	
	
	@Operation(
            summary = "Registra un Usuario",
            description = "Registra un Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
	@PostMapping("user")
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
		
		User user = modelMapper.map(userDTO, User.class);
		
		List<Phone> phones = userDTO.getPhones()
				  .stream()
				  .map(phone -> modelMapper.map(phone, Phone.class))
				  .collect(Collectors.toList());
		user.setPhones(phones);
		
		User userSaved=null;
		userSaved = userService.saveUser(user);
		
		UserDTO userSavedDTO = modelMapper.map(userSaved, UserDTO.class);
		userSavedDTO.setPhones(null);
		return ResponseEntity.ok(userSavedDTO);
	}

}
