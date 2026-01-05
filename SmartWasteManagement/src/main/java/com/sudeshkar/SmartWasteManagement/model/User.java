package com.sudeshkar.SmartWasteManagement.model;

import java.util.ArrayList;
import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "User_Table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	private  Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ServiceRequest> serviceRequests = new ArrayList<>();
	
	
	

}
