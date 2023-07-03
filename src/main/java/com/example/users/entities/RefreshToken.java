package com.example.users.entities;

import com.example.users.annotations.Ignore;
import com.example.users.global.variables.UserGlobalVariable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class RefreshToken extends Table{
    @Id
    @Ignore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userID;
    private String email;
    private String username;
    private String refreshToken;
    private Date createdTime;
    private Date expiredTime;
    private String role;
    @Ignore
    private int deletedStatus;

    public RefreshToken(User user){
        this.userID = user.getId();
        email = user.getEmail();
        username = user.getUsername();
        refreshToken = generateUUIDRefreshToken();
        role = user.getRole();
        createdTime = new Date();
        expiredTime = getExpirationDate();
        deletedStatus = 0;
    }

    public RefreshToken() {

    }

    private Date getExpirationDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, UserGlobalVariable.RefreshTokenExpirationTimeDate);
        return calendar.getTime();
    }

    private String generateUUIDRefreshToken(){
        return UUID.randomUUID().toString();
    }
}
