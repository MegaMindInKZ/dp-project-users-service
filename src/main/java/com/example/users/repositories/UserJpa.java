package com.example.users.repositories;

import com.example.users.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpa extends JpaRepository<UserModel, Long> {
    UserModel getUserModelByUsername(String username);
    UserModel getUserModelByEmail(String email);
}
