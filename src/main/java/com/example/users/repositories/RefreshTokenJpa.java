package com.example.users.repositories;

import com.example.users.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpa extends JpaRepository<RefreshToken, Long> {
    RefreshToken getRefreshTokenModelByRefreshToken(String refreshToken);
}
