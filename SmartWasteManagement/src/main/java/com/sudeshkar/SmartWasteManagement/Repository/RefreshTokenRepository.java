package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	Optional<RefreshToken> findByToken(String token);

    void deleteByEmail(String email);
}
