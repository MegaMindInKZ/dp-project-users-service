package com.example.users.repositories;

import com.example.users.entities.RefreshTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpa extends JpaRepository<RefreshTokenModel, Long> {
    RefreshTokenModel getRefreshTokenModelByRefreshToken(String refreshToken);
}
