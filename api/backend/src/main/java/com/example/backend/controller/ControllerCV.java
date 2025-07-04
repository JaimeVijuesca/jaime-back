package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.backend.dto.JobApplicationRequest;





@Controller
public class ControllerCV {

    // Importar el JobApplicationEntity y el servicio correspondiente

    // Inyectar el servicio de JobApplicationEntity
    // private final JobApplicationService jobApplicationService;

    // Constructor para inyectar el servicio
    public ControllerCV() {
        // this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/test")
    @ResponseBody
    public String getMethodName() {
        return "success"; // Cambia esto a la URL de tu página de éxito
    }
    


    @PostMapping("/add/formCV")
    @ResponseBody
    public String addFormCV(@ModelAttribute JobApplicationRequest jobRequest) {
        // Aquí puedes manejar el formulario enviado desde el frontend
        // Por ejemplo, guardar los datos en la base de datos o procesarlos de alguna manera
        System.out.println("Received job application: " + jobRequest);
        System.out.println("Company: " + jobRequest.getCompany());
        System.out.println("Position: " + jobRequest.getPosition());
        System.out.println("Status: " + jobRequest.getStatus());
        System.out.println("Details URL: " + jobRequest.getDetailsUrl());
        
        return "SUCCESS: Job application received - Company: " + jobRequest.getCompany() + ", Position: " + jobRequest.getPosition();
    }

    
}
