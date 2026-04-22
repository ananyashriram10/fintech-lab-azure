package com.example.lab4.repository;

import com.example.lab4.entity.CustomerContactInformation;
import com.example.lab4.entity.CustomerContactInformation.CustomerContactInformationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactInformationRepository
        extends JpaRepository<CustomerContactInformation, CustomerContactInformationId> {
}