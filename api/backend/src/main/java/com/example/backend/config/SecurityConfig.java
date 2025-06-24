package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/health", "/api/**", "/login/**", "/oauth2/**", "/css/**", "/js/**", "/error").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("https://jaime-bice.vercel.app/", true)
                .failureUrl("/login?error=true")
            );
        // Configuración para logout
        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("https://jaime-bice.vercel.app/") // Cambia esto a la URL de tu frontend
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        );
        return http.build();
    }
}