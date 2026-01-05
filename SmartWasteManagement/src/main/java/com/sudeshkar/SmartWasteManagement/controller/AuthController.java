package com.sudeshkar.SmartWasteManagement.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.auth.AuthResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.auth.LoginRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.auth.RefreshTokenRequestDto;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.sevice.RefreshTokenService;
import com.sudeshkar.SmartWasteManagement.sevice.UserService;
import com.sudeshkar.SmartWasteManagement.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
	
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto dto) {

        User user = userService.getByEmail(dto.email());

        if (!passwordEncoder.matches(dto.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole());
        String refreshToken = refreshTokenService.createToken(user.getEmail()).getToken();

        return new AuthResponseDto(accessToken, refreshToken, user.getRole().name());
    }
    
    @PostMapping("/refresh")
    public AuthResponseDto refresh(@RequestBody RefreshTokenRequestDto dto) {

        String newAccessToken = refreshTokenService.refreshAccessToken(dto.refreshToken());

        return new AuthResponseDto(
                newAccessToken,
                dto.refreshToken(),
                null
        );
    }
    
    @PostMapping("/logout")
    public String logout(Authentication auth) {

        refreshTokenService.deleteByEmail(auth.getName());
        SecurityContextHolder.clearContext();

        return "Logged out successfully";
    }


	

	
}
