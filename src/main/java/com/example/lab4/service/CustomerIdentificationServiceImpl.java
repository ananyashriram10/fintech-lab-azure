package com.example.lab4.service;

import com.example.lab4.entity.CustomerIdentification;
import com.example.lab4.entity.CustomerIdentification.CustomerIdentificationId;
import com.example.lab4.repository.CustomerIdentificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService {

    private final CustomerIdentificationRepository repository;

    public CustomerIdentificationServiceImpl(CustomerIdentificationRepository repository) {
        this.repository = repository;
    }
    @Override
    public CustomerIdentification update(Long customerId,
                                         String type,
                                         LocalDate effectiveDate,
                                         CustomerIdentification updated) {

        CustomerIdentification.CustomerIdentificationId id =
                new CustomerIdentification.CustomerIdentificationId(
                        customerId, type, effectiveDate);

        CustomerIdentification existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerIdentification not found"));

        existing.setCustomerIdentificationValue(updated.getCustomerIdentificationValue());

        return repository.save(existing);
    }


    @Override
    public CustomerIdentification create(CustomerIdentification customerIdentification) {
        return repository.save(customerIdentification);
    }

    @Override
    public CustomerIdentification getById(Long customerId,
                                          String identificationType,
                                          LocalDate effectiveDate) {
        return repository.findById(
                new CustomerIdentificationId(customerId, identificationType, effectiveDate)
        ).orElseThrow();
    }

    @Override
    public List<CustomerIdentification> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long customerId,
                       String identificationType,
                       LocalDate effectiveDate) {

        repository.deleteById(
                new CustomerIdentificationId(customerId, identificationType, effectiveDate)
        );
    }
}
