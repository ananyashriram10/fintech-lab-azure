package com.example.lab4.repository;

import com.example.lab4.entity.CustomerName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNameRepository
        extends JpaRepository<CustomerName, CustomerName.CustomerNameId> {}
