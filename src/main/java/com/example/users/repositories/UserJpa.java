package com.example.users.repositories;

import com.example.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpa extends JpaRepository<User, Long> {
    User getUserModelByUsername(String username);
    User getUserModelByEmail(String email);
}
