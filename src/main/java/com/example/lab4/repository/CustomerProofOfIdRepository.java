package com.example.lab4.repository;

import com.example.lab4.entity.CustomerProofOfId;
import com.example.lab4.entity.CustomerProofOfId.CustomerProofOfIdId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProofOfIdRepository
        extends JpaRepository<CustomerProofOfId, CustomerProofOfIdId> {
}