package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.JobApplicationRequest;
import com.example.backend.entity.JobApplication;
import com.example.backend.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    // Constructor para inyección de dependencias
    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
    // Metodo para enviar la solicitud de empleo

    public void submitJobApplication(JobApplicationRequest request) {

        // Convertir JobApplicationRequest a JobApplication entity

        try {
            JobApplication jobApplication = new JobApplication();
            jobApplication.setCompany(request.getCompany());
            jobApplication.setPosition(request.getPosition());
            jobApplication.setStatus(request.getStatus());
            jobApplication.setDetailsUrl(request.getDetailsUrl());
            // Aquí puedes agregar la lógica para enviar la solicitud de empleo
            System.out.println("Service is processing job application: " + request);

            // Guardar los datos de la solicitud de empleo

            jobApplicationRepository.save(jobApplication);
            // Aquí puedes agregar la lógica para enviar la solicitud de empleo
        } catch (Exception e) {
            System.out.println("Error processing job application: " + e.toString());
        }

    }

}