package com.security.oauthjwt.config.jwt;

import com.security.oauthjwt.config.oauth.CustomOauthUser;
import com.security.oauthjwt.domain.User;
import com.security.oauthjwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String secretKey;
    private byte[] keyBytes;
    private MacAlgorithm hs256;

    private static String ALG_HEADER_NAME = "alg";
    private static String TOKEN_TYPE = "Bearer";

    private final UserRepository userRepository;

    /** 24시간 (하루) */
    private static Long ACCESS_TOKEN_VALID_PERIOD = 60 * 60 * 24L;
    /** 720시간 (30일) */
    private static Long REFRESH_TOKEN_VALID_PERIOD = 60 * 60 * 24 * 30L;

    @PostConstruct
    private void init() throws NoSuchAlgorithmException {
        // SHA-256 해시알고리즘으로 해싱처리
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(secretKey.getBytes(StandardCharsets.UTF_8));
        keyBytes = Base64.getEncoder().encode(hash);
        hs256 = Jwts.SIG.HS256;
    }


    // create jwt
    public TokenDto create(Long userId) {

        LocalDateTime now = LocalDateTime.now();

        String accessToken = Jwts.builder()
                .header()
                .add(ALG_HEADER_NAME, hs256.getId())
                .and()
                .subject(String.valueOf(userId))
                .issuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
                .expiration(Date.from(now.plusSeconds(ACCESS_TOKEN_VALID_PERIOD).toInstant(ZoneOffset.UTC)))
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();

        String refreshToken = Jwts.builder()
                .subject(String.valueOf(userId))
                .expiration(Date.from(now.plusSeconds(REFRESH_TOKEN_VALID_PERIOD).toInstant(ZoneOffset.UTC)))
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();

        return TokenDto.builder()
                .type(TOKEN_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(ACCESS_TOKEN_VALID_PERIOD)
                .build();
    }

    public boolean validate(String token) {
        log.info("validate token: {}", token);
        try {
            // Parse the compact JWS
            return !getPayloadOf(token)
                        .getExpiration().before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Claims getPayloadOf(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(keyBytes)).build()
                .parseSignedClaims(token).getPayload();
    }

    public Authentication getAuthentication(String token) {
        Long userId = Long.parseLong(getPayloadOf(token).getSubject());
        User user = userRepository.findById(userId).get();

        CustomOauthUser customOauthUser = new CustomOauthUser(user.getId(), user.getEmail(), user.getName(), UUID.randomUUID().toString(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

        return new UsernamePasswordAuthenticationToken(customOauthUser, null, customOauthUser.getAuthorities());
    }
}
