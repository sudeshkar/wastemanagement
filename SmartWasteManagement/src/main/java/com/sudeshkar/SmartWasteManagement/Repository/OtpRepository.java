package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.OtpVerification;

public interface OtpRepository extends JpaRepository<OtpVerification, Long> {
	Optional<OtpVerification> findByEmailAndOtp(String email, String otp);
    Optional<OtpVerification> findTopByEmailOrderByExpiryTimeDesc(String email);

}
