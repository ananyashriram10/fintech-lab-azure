package com.example.lab4.service;

import com.example.lab4.entity.CustomerClassification;
import java.util.List;

public interface CustomerClassificationService {

    CustomerClassification create(CustomerClassification customerClassification);

    CustomerClassification update(Long id, CustomerClassification customerClassification);

    CustomerClassification getById(Long id);

    List<CustomerClassification> getAll();

    void delete(Long id);
}
