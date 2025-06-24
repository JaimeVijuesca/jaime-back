package com.example.backend.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class LoginController {
    
    @GetMapping("/")
    public RedirectView home() {
        // Redirige al frontend después del login exitoso
        // return new RedirectView("https://jaime-bice.vercel.app/");
        return new RedirectView("http://localhost:3001/"); // Cambia esto a la URL de tu frontend
    }

    

    @GetMapping("/api/user")
    @ResponseBody
    public Object user(@AuthenticationPrincipal OAuth2User principal) {
        // Devuelve los detalles del usuario autenticado
        // Si no hay usuario autenticado, devuelve un mensaje
        return principal != null ? principal.getAttributes() : "No user authenticated";
    }
    
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        // Endpoint de salud para verificar que el backend está funcionando
        // Puedes agregar lógica adicional para verificar la salud del sistema
        // como conexión a la base de datos, servicios externos, etc.
        return "Backend is running!";
    }
    
    @GetMapping("/api/status")
    @ResponseBody
    public String status() {
        // Endpoint para verificar el estado de la API
        return "API is working correctly";
    }

    // Endpoint para logout
    @GetMapping("/logout")
    public RedirectView logout() {
        // Redirige al usuario a la página de inicio después del logout
        return new RedirectView("http://localhost:3001/"); // Cambia esto a la URL de tu frontend
    }
}