package com.example.users.utils;

import com.example.users.entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static String salt = "be44361a-96c2-4edd-8789-9d0b1593af61";
    public static String getHashedPasswordSHA256(String password)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean check_password(User user, String password) {
        String userPassword = user.getPassword();
        String passwordHashSHA256 = PasswordUtil.getHashedPasswordSHA256(password);

        return passwordHashSHA256.equals(userPassword);
    }
}
