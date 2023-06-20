package com.example.users.entities;

import com.example.users.annotations.Ignore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class UserModel extends Table {
    @Ignore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String username;
    private String fullname;
    private String email;
    @Ignore
    private String password;
    private String role;
    private Date createdTime;
    private Date lastLogin;
    private int emailVerified;
    @Ignore
    private int deletedStatus=0;
}
