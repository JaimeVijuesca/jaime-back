package com.example.backend.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class JobApplicationRequest {
    private String company;
    private String position;
    private String status;
    private String detailsUrl;
    private MultipartFile companyLogo;

    // Puedes agregar más campos según sea necesario, como fecha de solicitud,
    // nombre del solicitante, etc.

    // Constructores
    public JobApplicationRequest() {
    }

    public JobApplicationRequest(String company, String position, String status, String detailsUrl,
            MultipartFile companyLogo) {
        this.company = company;
        this.position = position;
        this.status = status;
        this.detailsUrl = detailsUrl;
        this.companyLogo = companyLogo;

    }

    // Getters y setters
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public MultipartFile getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(MultipartFile companyLogo) {
        this.companyLogo = companyLogo;
    }

    @Override
    public String toString() {
        return "JobApplicationRequest{" +
                "company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                ", detailsUrl='" + detailsUrl + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                '}';
    }
}
