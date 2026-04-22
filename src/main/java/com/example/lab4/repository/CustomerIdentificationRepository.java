package com.example.lab4.repository;

import com.example.lab4.entity.CustomerIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerIdentificationRepository
        extends JpaRepository<CustomerIdentification, CustomerIdentification.CustomerIdentificationId> {}
