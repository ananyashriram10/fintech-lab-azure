package com.example.lab4.service;

import com.example.lab4.entity.CustomerClassification;
import com.example.lab4.repository.CustomerClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerClassificationServiceImpl implements CustomerClassificationService {

    private final CustomerClassificationRepository repository;

    public CustomerClassificationServiceImpl(CustomerClassificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerClassification create(CustomerClassification customerClassification) {
        return repository.save(customerClassification);
    }

    @Override
    public CustomerClassification update(Long id, CustomerClassification customerClassification) {

        CustomerClassification existing = repository.findById(id).orElseThrow();

        existing.setCustomerClassificationType(
                customerClassification.getCustomerClassificationType()
        );

        existing.setCustomerClassificationValue(
                customerClassification.getCustomerClassificationValue()
        );

        existing.setEffectiveDate(
                customerClassification.getEffectiveDate()
        );

        return repository.save(existing);
    }

    @Override
    public CustomerClassification getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<CustomerClassification> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
