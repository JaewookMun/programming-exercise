package com.example.jpaboard.service;

public class MemberService {

    /**
     * temporally encode password like this
     */
    public static String encodePassword(String rawPassword) {
        return String.valueOf(rawPassword.hashCode());
    }
}
