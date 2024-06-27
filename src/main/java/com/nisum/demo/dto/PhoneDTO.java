package com.nisum.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
	
	private String number;
	@JsonProperty("citycode")
	private String cityCode;
	@JsonProperty("contrycode")
	private String countryCode;
	
	
	

}
