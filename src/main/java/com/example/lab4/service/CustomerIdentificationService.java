package com.example.lab4.service;

import com.example.lab4.entity.CustomerIdentification;
import java.time.LocalDate;
import java.util.List;

public interface CustomerIdentificationService {

    CustomerIdentification create(CustomerIdentification customerIdentification);

    CustomerIdentification getById(Long customerId,
                                   String identificationType,
                                   LocalDate effectiveDate);

    List<CustomerIdentification> getAll();

    CustomerIdentification update(Long customerId,
                                  String identificationType,
                                  LocalDate effectiveDate,
                                  CustomerIdentification customerIdentification);

    void delete(Long customerId,
                String identificationType,
                LocalDate effectiveDate);
}

