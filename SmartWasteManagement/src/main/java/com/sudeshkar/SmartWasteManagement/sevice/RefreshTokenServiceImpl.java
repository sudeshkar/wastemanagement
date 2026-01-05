package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.RefreshTokenRepository;
import com.sudeshkar.SmartWasteManagement.model.RefreshToken;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
	
	 private final RefreshTokenRepository repo;
	 private final JwtUtil jwtUtil;
	 private final UserService userService;
	 
	 @Override
	    public RefreshToken createToken(String email) {

	        RefreshToken token = new RefreshToken();
	        token.setEmail(email);
	        token.setToken(UUID.randomUUID().toString());
	        token.setExpiryTime(LocalDateTime.now().plusDays(7));

	        return repo.save(token);
	    }

	    @Override
	    public String refreshAccessToken(String refreshToken) {

	        RefreshToken token = repo.findByToken(refreshToken)
	                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

	        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
	            throw new RuntimeException("Refresh token expired");
	        }

	        User user = userService.getByEmail(token.getEmail());

	        return jwtUtil.generateToken(user.getEmail(), user.getRole());
	    }

	    @Override
	    @Transactional
	    public void deleteByEmail(String email) {
	        repo.deleteByEmail(email);
	    }
}
