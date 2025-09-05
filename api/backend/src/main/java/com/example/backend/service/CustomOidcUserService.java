package com.example.backend.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.stereotype.Service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;

@Service
public class CustomOidcUserService
        implements org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OidcUserRequest, OidcUser> {

    private final OidcUserService delegate = new OidcUserService();
    private final UserRepository userRepository;

    public CustomOidcUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        // 1) Cargar usuario desde Google
        OidcUser oidcUser = delegate.loadUser(userRequest);

        System.out.println("🚀 Entró a CustomOidcUserService con sub=" + oidcUser.getSubject());

        // 2) Extraer datos
        String provider = userRequest.getClientRegistration().getRegistrationId(); // "google"
        String providerId = oidcUser.getSubject(); // "sub"
        String email = oidcUser.getEmail(); // requiere scope "email"
        String name = oidcUser.getFullName(); // o (String) oidcUser.getClaims().get("name")
        String picture = (String) oidcUser.getClaims().get("picture");

        // 3) Guardar/actualizar en BD (buscar primero por provider+providerId; si no,
        // por email)
        User user = userRepository.findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> email != null
                        ? userRepository.findByEmail(email).orElse(new User())
                        : new User());

        user.setProvider(provider);
        user.setProviderId(providerId);
        user.setEmail(email);
        user.setName(name);
        user.setPicture(picture);
        userRepository.save(user);

        // 4) Devolver OidcUser con ROLE_USER añadido
        Set<GrantedAuthority> authorities = new HashSet<>(oidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
