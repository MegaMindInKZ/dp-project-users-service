package com.example.users.data.repositories;

import com.example.users.data.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpa extends JpaRepository<RefreshToken, Long> {
    RefreshToken getRefreshTokenModelByRefreshToken(String refreshToken);
}
