package com.security.oauthjwt.config.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.oauthjwt.config.jwt.JwtTokenProvider;
import com.security.oauthjwt.config.jwt.TokenDto;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("authentication success");
        CustomOauthUser authenticatedUser = (CustomOauthUser) authentication.getPrincipal();

        TokenDto tokenDto = tokenProvider.create(authenticatedUser.getId());
        String token = new ObjectMapper().writeValueAsString(tokenDto);

        response.addHeader("Authorization", tokenDto.getAccessToken());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter writer = response.getWriter();
        writer.println(token);
        writer.flush();
    }
}
