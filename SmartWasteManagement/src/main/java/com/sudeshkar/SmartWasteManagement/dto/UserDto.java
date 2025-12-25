package com.sudeshkar.SmartWasteManagement.dto;

import com.sudeshkar.SmartWasteManagement.Enum.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private Long id;
	private String email;
	private  Role role;
}
