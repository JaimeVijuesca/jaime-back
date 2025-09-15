package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.backend.dto.JobApplicationRequest;
import com.example.backend.entity.JobApplication;
import com.example.backend.service.JobApplicationService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerCV {

    @Autowired
    private JobApplicationService jobApplicationService;

    public ControllerCV(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;

    }

    @PostMapping("api/add/formCV")
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

    // Send the user id to see the offers of the user

    @GetMapping("/cv")
    @ResponseBody
    public List<JobApplication> getJobApplicationsForAuthenticatedUser(@AuthenticationPrincipal OidcUser oidcUser) {
        String providerId = oidcUser.getSubject(); // Este es el Google ID
        System.out.println("Authenticated user providerId: " + providerId);
        return jobApplicationService.getJobApplicationsByUserId(providerId);
    }
}
