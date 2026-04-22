package com.example.lab4.service;

import com.example.lab4.entity.CustomerContactInformation;
import com.example.lab4.entity.CustomerContactInformation.CustomerContactInformationId;
import com.example.lab4.repository.CustomerContactInformationRepository;
import com.example.lab4.service.CustomerContactInformationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerContactInformationServiceImpl
        implements CustomerContactInformationService {

    private final CustomerContactInformationRepository repository;

    public CustomerContactInformationServiceImpl(
            CustomerContactInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerContactInformation create(
            CustomerContactInformation customerContactInformation) {

        return repository.save(customerContactInformation);
    }

    @Override
    public CustomerContactInformation getById(
            Long customerId,
            String contactType,
            LocalDate effectiveDate) {

        CustomerContactInformationId id =
                new CustomerContactInformationId(customerId, contactType, effectiveDate);

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer contact not found"));
    }

    @Override
    public List<CustomerContactInformation> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long customerId,
                       String contactType,
                       LocalDate effectiveDate) {

        CustomerContactInformationId id =
                new CustomerContactInformationId(customerId, contactType, effectiveDate);

        repository.deleteById(id);
    }

    @Override
    public CustomerContactInformation update(
            Long customerId,
            String contactType,
            LocalDate effectiveDate,
            CustomerContactInformation updated) {

        CustomerContactInformationId id =
                new CustomerContactInformationId(customerId, contactType, effectiveDate);

        CustomerContactInformation existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer contact not found"));

        existing.setCustomerContactValue(updated.getCustomerContactValue());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());

        return repository.save(existing);
    }
}