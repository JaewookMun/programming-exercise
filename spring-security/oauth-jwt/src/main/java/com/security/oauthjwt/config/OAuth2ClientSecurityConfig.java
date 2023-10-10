package com.security.oauthjwt.config;

import com.security.oauthjwt.config.jwt.JwtAuthenticationFilter;
import com.security.oauthjwt.config.jwt.JwtTokenProvider;
import com.security.oauthjwt.config.oauth.CustomOauth2UsersService;
import com.security.oauthjwt.config.oauth.OAuthAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOauth2UsersService oauth2UsersService;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .httpBasic(httpBasic -> httpBasic.disable())
                .oauth2Login(
                        oauth -> oauth
                                    .userInfoEndpoint(userInfo -> userInfo.userService(oauth2UsersService))
                                    .successHandler(oAuthAuthenticationSuccessHandler))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
