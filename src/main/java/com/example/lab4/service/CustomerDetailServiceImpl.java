package com.example.lab4.service;

import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.repository.CustomerDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository repository;

    public CustomerDetailServiceImpl(CustomerDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerDetail create(CustomerDetail customerDetail) {
        return repository.save(customerDetail);
    }

    @Override
    public CustomerDetail update(Long id, CustomerDetail customerDetail) {
        CustomerDetail existing = repository.findById(id).orElseThrow();
        existing.setCustomerFullName(customerDetail.getCustomerFullName());
        existing.setCustomerGender(customerDetail.getCustomerGender());
        existing.setCustomerType(customerDetail.getCustomerType());
        existing.setCustomerDateOfBirth(customerDetail.getCustomerDateOfBirth());
        existing.setCustomerPreferredLanguage(customerDetail.getCustomerPreferredLanguage());
        existing.setCustomerStatus(customerDetail.getCustomerStatus());
        existing.setCustomerCountryOfOrigin(customerDetail.getCustomerCountryOfOrigin());
        return repository.save(existing);
    }

    @Override
    public CustomerDetail getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<CustomerDetail> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
