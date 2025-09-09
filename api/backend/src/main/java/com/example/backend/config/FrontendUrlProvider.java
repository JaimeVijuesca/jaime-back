package com.example.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FrontendUrlProvider {

    private final String frontendUrl;

    public FrontendUrlProvider(@Value("${app.frontend.url:http://localhost:3001}") String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }

    public String getFrontendUrl() {
        return frontendUrl;
    }
}
