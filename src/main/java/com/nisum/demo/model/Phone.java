package com.nisum.demo.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Phone implements Serializable{

	private static final long serialVersionUID = 4684857300095034133L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityCode;

    private String countryCode ;

    private String number;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
