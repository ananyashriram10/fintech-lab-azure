package com.example.lab4.service;

import com.example.lab4.entity.CustomerAddress;
import com.example.lab4.entity.CustomerAddress.CustomerAddressId;
import com.example.lab4.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepository repository;

    public CustomerAddressServiceImpl(CustomerAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerAddress create(CustomerAddress customerAddress) {
        return repository.save(customerAddress);
    }

    @Override
    public CustomerAddress getById(Long customerId,
                                   String addressType,
                                   LocalDate effectiveDate) {

        CustomerAddressId id =
                new CustomerAddressId(customerId, addressType, effectiveDate);

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerAddress not found"));
    }

    @Override
    public List<CustomerAddress> getAll() {
        return repository.findAll();
    }

    @Override
    public CustomerAddress update(Long customerId,
                                  String addressType,
                                  LocalDate effectiveDate,
                                  CustomerAddress updated) {

        CustomerAddressId id =
                new CustomerAddressId(customerId, addressType, effectiveDate);

        CustomerAddress existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerAddress not found"));

        existing.setCustomerAddressValue(updated.getCustomerAddressValue());

        return repository.save(existing);
    }

    @Override
    public void delete(Long customerId,
                       String addressType,
                       LocalDate effectiveDate) {

        CustomerAddressId id =
                new CustomerAddressId(customerId, addressType, effectiveDate);

        repository.deleteById(id);
    }
}
