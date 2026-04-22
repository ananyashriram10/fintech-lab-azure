package com.example.lab4.service;

import com.example.lab4.entity.CustomerContactInformation;

import java.time.LocalDate;
import java.util.List;

public interface CustomerContactInformationService {

    CustomerContactInformation create(CustomerContactInformation customerContactInformation);

    CustomerContactInformation getById(Long customerId,
                                       String contactType,
                                       LocalDate effectiveDate);

    List<CustomerContactInformation> getAll();

    void delete(Long customerId,
                String contactType,
                LocalDate effectiveDate);

    CustomerContactInformation update(Long customerId,
                                      String contactType,
                                      LocalDate effectiveDate,
                                      CustomerContactInformation updated);
}