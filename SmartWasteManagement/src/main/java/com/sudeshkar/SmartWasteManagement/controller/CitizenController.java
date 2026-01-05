package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.Enum.Role;
import com.sudeshkar.SmartWasteManagement.dto.auth.CitizenRegisterDto;
import com.sudeshkar.SmartWasteManagement.dto.auth.CitizenVerifyOtpDto;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.sevice.OtpService;
import com.sudeshkar.SmartWasteManagement.sevice.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {
	
	private final OtpService otpService;
	private final PasswordEncoder encoder;
	private final UserService userService;
	
	@PostMapping("/register")
    public String register(@RequestBody CitizenRegisterDto dto) {
        // generate OTP
        // save OTP with expiry
        // send email
        return "OTP sent";
    }
	
	@PostMapping("/request-otp")
	public String requestOtp(@RequestBody CitizenRegisterDto dto) {
	    otpService.sendOtp(dto.email());
	    
	    return "OTP sent to email";
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestBody CitizenVerifyOtpDto dto) {

	    otpService.verifyOtp(dto.email(), dto.otp());
	    if (!dto.password().matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$")) {
	        throw new RuntimeException("Weak password");
	    }


	    if (userService.existsByEmail(dto.email())) {
	        throw new RuntimeException("User already exists");
	    }
	    

	    User user = new User();
	    user.setEmail(dto.email());
	    user.setPasswordHash(encoder.encode(dto.password()));
	    user.setRole(Role.CITIZEN);

	    userService.createUser(user);
	    return "Citizen registered successfully";
	}

}
