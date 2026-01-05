package com.sudeshkar.SmartWasteManagement.dto.auth;

import com.sudeshkar.SmartWasteManagement.Enum.Role;

public record AdminCreateUserDto(
		String email,
        String password,
        Role role
		) {

}
