package com.sudeshkar.SmartWasteManagement.dto.auth;

public record  CitizenVerifyOtpDto(
		 String email,
        String otp,
        String password
		) {

}
