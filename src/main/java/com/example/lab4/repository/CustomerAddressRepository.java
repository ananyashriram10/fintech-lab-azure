package com.example.lab4.repository;

import com.example.lab4.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository
        extends JpaRepository<CustomerAddress, CustomerAddress.CustomerAddressId> {}
