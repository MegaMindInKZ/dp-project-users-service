package com.example.users.data.repositories;

import com.example.users.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpa extends JpaRepository<User, Long> {
    User getUserModelByUsername(String username);
    User getUserModelByEmail(String email);
}
