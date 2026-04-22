package com.example.lab4.service;

import com.example.lab4.entity.CustomerAddress;
import java.time.LocalDate;
import java.util.List;

public interface CustomerAddressService {

    CustomerAddress create(CustomerAddress customerAddress);

    CustomerAddress getById(Long customerId,
                            String addressType,
                            LocalDate effectiveDate);

    List<CustomerAddress> getAll();

    CustomerAddress update(Long customerId,
                           String addressType,
                           LocalDate effectiveDate,
                           CustomerAddress customerAddress);

    void delete(Long customerId,
                String addressType,
                LocalDate effectiveDate);
}
