package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.OtpRepository;
import com.sudeshkar.SmartWasteManagement.model.OtpVerification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService{
	
	private final OtpRepository otpRepo;
	private final EmailService emailService;
	@Override
	public void sendOtp(String email) {
		
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        OtpVerification entity = new OtpVerification();
        entity.setEmail(email);
        entity.setOtp(otp);
        entity.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepo.save(entity);
        
        String subject = "OTP Verification";
        String body = "Your OTP is: " + otp + "\nThis OTP is valid for 5 minutes.";

        emailService.sendEmail(entity.getEmail(), subject, body);

        
		
	}

	@Override
	public void verifyOtp(String email, String otp) {
		
		OtpVerification data = otpRepo.findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));
		
		if (data.getAttempts() >= 3) {
		    throw new RuntimeException("Too many OTP requests. Try later.");
		}

        if (data.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        data.setVerified(true);
        otpRepo.save(data);
		
	}

}
