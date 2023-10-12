package com.security.oauthjwt.config.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.oauthjwt.config.jwt.JwtTokenProvider;
import com.security.oauthjwt.config.jwt.TokenDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter writer = response.getWriter();
        writer.println(new ObjectMapper().writeValueAsString(tokenDto));
        writer.flush();
    }
}