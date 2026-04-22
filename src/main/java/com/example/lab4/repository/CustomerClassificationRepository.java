package com.example.lab4.repository;

import com.example.lab4.entity.CustomerClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerClassificationRepository extends JpaRepository<CustomerClassification, Long> {}
