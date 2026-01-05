package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.auth.AdminCreateUserDto;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.sevice.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	private final PasswordEncoder encoder;
	
	@PostMapping("/create-user")
    public String createUser(@RequestBody AdminCreateUserDto dto) {

        User user = new User();
        user.setEmail(dto.email());
        user.setPasswordHash(encoder.encode(dto.password()));
        user.setRole(dto.role());

        userService.createUser(user);
        return "User created successfully";
    }

}
