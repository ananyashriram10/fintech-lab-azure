package com.example.lab4.repository;

import com.example.lab4.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {}
