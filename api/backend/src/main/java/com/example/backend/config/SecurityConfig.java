package com.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletRequest; // <-- usa jakarta

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private FrontendUrlProvider appProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository)
            throws Exception {

        System.out.println("DEBUG: " + appProperties);
        System.out.println("DEBUG URL: " + appProperties.getFrontendUrl());

        OAuth2AuthorizationRequestResolver defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository, "/oauth2/authorization");

        OAuth2AuthorizationRequestResolver customResolver = new OAuth2AuthorizationRequestResolver() {
            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
                OAuth2AuthorizationRequest original = defaultResolver.resolve(request);
                if (original == null)
                    return null;
                return OAuth2AuthorizationRequest.from(original)
                        .additionalParameters(params -> params.put("prompt", "select_account"))
                        .build();
            }

            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
                OAuth2AuthorizationRequest original = defaultResolver.resolve(request, clientRegistrationId);
                if (original == null)
                    return null;
                return OAuth2AuthorizationRequest.from(original)
                        .additionalParameters(params -> params.put("prompt", "select_account"))
                        .build();
            }
        };

        http
                .cors(org.springframework.security.config.Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/health", "/api/**", "/login/**", "/oauth2/**", "/css/**", "/js/**",
                                "/error")
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .authorizationEndpoint(authorization -> authorization
                                .authorizationRequestResolver(customResolver))
                        .defaultSuccessUrl(appProperties.getFrontendUrl(), true)
                        .failureUrl("/login?error=true"));
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl(appProperties.getFrontendUrl())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        return http.build();
    }
}