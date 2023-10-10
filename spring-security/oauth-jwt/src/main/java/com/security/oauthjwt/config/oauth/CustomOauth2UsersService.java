package com.security.oauthjwt.config.oauth;

import com.security.oauthjwt.domain.User;
import com.security.oauthjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOauth2UsersService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        String registrationId = clientRegistration.getRegistrationId();

        Map<String, Object> attributes = (Map<String, Object>) oAuth2User.getAttribute("response");

        String email = (String) attributes.get("email");
        Optional<User> findUser = userRepository.findByEmail(email);

        User user;
        if (findUser.isEmpty()) {
            user = new User((String) attributes.get("name"), email);
            userRepository.save(user);
        } else user = findUser.get();

        log.info("clientRegistration: {}, accessToken: {}, registrationId: {}", clientRegistration, accessToken, registrationId);

        return new CustomOauthUser(user.getId(), email, user.getName(), UUID.randomUUID().toString(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}