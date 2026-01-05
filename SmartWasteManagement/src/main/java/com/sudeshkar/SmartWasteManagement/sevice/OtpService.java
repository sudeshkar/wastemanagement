package com.sudeshkar.SmartWasteManagement.sevice;

public interface OtpService {
	
	void sendOtp(String email);
    void verifyOtp(String email, String otp);
}
