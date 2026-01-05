package com.sudeshkar.SmartWasteManagement.dto.auth;

public record AuthResponseDto(
		String accessToken,
        String refreshToken,
        String role
		) {

}
