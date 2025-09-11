package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.backend.dto.JobApplicationRequest;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.JobApplicationService;

@Controller
public class ControllerCV {

    @Autowired
    private JobApplicationService jobApplicationService;

    public ControllerCV(JobApplicationService jobApplicationService, UserRepository userRepository) {
        this.jobApplicationService = jobApplicationService;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String getMethodName() {
        return "success"; // Cambia esto a la URL de tu página de éxito
    }

    @PostMapping("/add/formCV")
    @ResponseBody
    public String addFormCV(@ModelAttribute JobApplicationRequest jobRequest) {
        // Aquí puedes manejar el formulario enviado desde el frontend
        // Por ejemplo, guardar los datos en la base de datos o procesarlos de alguna
        // manera

        System.out.println("Received job application: " + jobRequest);
        System.out.println("Company: " + jobRequest.getCompany());
        System.out.println("Position: " + jobRequest.getPosition());
        System.out.println("Status: " + jobRequest.getStatus());
        System.out.println("Details URL: " + jobRequest.getDetailsUrl());
        System.out.println("Company Logo: " + jobRequest.getCompanyLogo());
        System.out.println("User ID: " + jobRequest.getUserId());
       

        jobApplicationService.submitJobApplication(jobRequest);


        return "SUCCESS: Job application submitted";
    }

}
