package com.security.oauthjwt.config.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter
@ToString
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private String type;
    private Long expiresIn;
}
