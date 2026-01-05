package com.sudeshkar.SmartWasteManagement.sevice;

import com.sudeshkar.SmartWasteManagement.model.RefreshToken;

public interface RefreshTokenService {
	RefreshToken createToken(String email);
    String refreshAccessToken(String refreshToken);
    void deleteByEmail(String email);
}
