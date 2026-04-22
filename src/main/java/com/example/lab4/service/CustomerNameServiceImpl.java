package com.example.lab4.service;

import com.example.lab4.entity.CustomerName;
import com.example.lab4.entity.CustomerName.CustomerNameId;
import com.example.lab4.repository.CustomerNameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerNameServiceImpl implements CustomerNameService {

    private final CustomerNameRepository repository;

    public CustomerNameServiceImpl(CustomerNameRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerName create(CustomerName customerName) {
        return repository.save(customerName);
    }
    @Override
    public CustomerName update(Long customerId,
                               String nameType,
                               LocalDate effectiveDate,
                               CustomerName updated) {

        CustomerName.CustomerNameId id =
                new CustomerName.CustomerNameId(customerId, nameType, effectiveDate);

        CustomerName existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerName not found"));

        existing.setCustomerNameValue(updated.getCustomerNameValue());

        return repository.save(existing);
    }



    @Override
    public CustomerName getById(Long customerId, String nameType, LocalDate effectiveDate) {
        return repository.findById(
                new CustomerNameId(customerId, nameType, effectiveDate)
        ).orElseThrow();
    }

    @Override
    public List<CustomerName> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long customerId, String nameType, LocalDate effectiveDate) {
        repository.deleteById(
                new CustomerNameId(customerId, nameType, effectiveDate)
        );
    }
}
