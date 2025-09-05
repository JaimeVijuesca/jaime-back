package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}