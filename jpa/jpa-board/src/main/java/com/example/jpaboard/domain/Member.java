package com.example.jpaboard.domain;

import com.example.jpaboard.service.MemberService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    public Member(String name, String password) {
        this.name = name;
        this.password = MemberService.encodePassword(password);
    }
}
