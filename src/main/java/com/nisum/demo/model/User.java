package com.nisum.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER_SIGN")
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = -4579842707190656694L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)    
	private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime modified;
    
    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime lastLogin;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean isActive;
    
    @Cascade({CascadeType.ALL})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Phone> phones = new ArrayList<Phone>();



}
