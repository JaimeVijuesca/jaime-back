package com.example.backend.service;

import java.util.List;


import org.springframework.stereotype.Service;


import com.example.backend.dto.JobApplicationRequest;
import com.example.backend.entity.JobApplication;
import com.example.backend.entity.User;
import com.example.backend.repository.JobApplicationRepository;
import com.example.backend.repository.UserRepository;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final UserRepository userRepository;

    

    // Constructor para inyección de dependencias
    public JobApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }
    // Metodo para enviar la solicitud de empleo

    public void submitJobApplication(JobApplicationRequest request) {

        // Convertir JobApplicationRequest a JobApplication entity

        try {
            JobApplication jobApplication = new JobApplication();
            jobApplication.setCompany(request.getCompany());
            jobApplication.setPosition(request.getPosition());
            jobApplication.setStatus(request.getStatus());  
            String logoUrl = request.getCompanyLogo() != null ? request.getCompanyLogo().getOriginalFilename() : null;
            jobApplication.setCompanyLogo(logoUrl);
            jobApplication.setDetailsUrl(request.getDetailsUrl());

            User user = userRepository.findByProviderId(request.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

            jobApplication.setUser(user);

            // Aquí puedes agregar la lógica para enviar la solicitud de empleo
            System.out.println("Service is processing job application: " + request);

            // Guardar los datos de la solicitud de empleo

            jobApplicationRepository.save(jobApplication);
            // Aquí puedes agregar la lógica para enviar la solicitud de empleo
        } catch (Exception e) {
            System.out.println("Error processing job application: " + e.toString());
        }

    }

    public List<JobApplication> getJobApplicationsByUserId(String providerId) {
        return jobApplicationRepository.findByUser_ProviderId(providerId);
    }


  
}