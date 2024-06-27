package com.nisum.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nisum.demo.config.SecurityConfiguration;
import com.nisum.demo.dto.UserDTO;
import com.nisum.demo.model.User;
import com.nisum.demo.service.UserService;

@Import(SecurityConfiguration.class)
@WebMvcTest
public class UserControllerTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 private ModelMapper modelMapper;
	 
	 @MockBean
	 private UserService userService;
	
	@Test
    public void whenGetAllAssets_thenControlFlowCorrect() throws Exception {
		String jsonData = "{\r\n"
				+ "    \"name\":\"test\",\r\n"
				+ "    \"password\":\"Baeldung20@\",\r\n"
				+ "    \"email\":\"alejandrorojas@dominio.cl\",\r\n"
				+ "     \"phones\": [\r\n"
				+ "                    {\r\n"
				+ "                    \"number\": \"1234567\",\r\n"
				+ "                    \"citycode\": \"1\",\r\n"
				+ "                    \"contrycode\": \"57\"\r\n"
				+ "                    }\r\n"
				+ "               ]\r\n"
				+ "}";
		
		User user = new User();
		user.setEmail("alejandrorojas@dominio.cl");
		user.setPassword("Baeldung20@");
		user.setName("test");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("alejandrorojas@dominio.cl");
		userDTO.setIsActive(Boolean.TRUE);
		userDTO.setPassword("Baeldung20@");
		userDTO.setName("test");
		
		
		Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), eq(User.class))).thenReturn(user);
		
        Mockito.when(userService.saveUser(any())).thenReturn(user);

        this.mockMvc.perform( MockMvcRequestBuilders
      	      .post("/user")
      	      .content(jsonData)
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists());

        Mockito.verify(userService, Mockito.times(1)).saveUser(any());
    }

}
