package com.security.oauthjwt.config.jwt;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    @Test
    void t() throws NoSuchAlgorithmException {
        String secretKey = "candidate";
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(secretKey.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append(0);

            hexString.append(hex);
        }
        System.out.println("hexString = " + hexString);

    }

}