package com.example.lab4.service;

import com.example.lab4.entity.CustomerName;
import java.time.LocalDate;
import java.util.List;

public interface CustomerNameService {

    CustomerName create(CustomerName customerName);

    CustomerName getById(Long customerId, String nameType, LocalDate effectiveDate);

    List<CustomerName> getAll();

    CustomerName update(Long customerId,
                        String nameType,
                        LocalDate effectiveDate,
                        CustomerName customerName);

    void delete(Long customerId, String nameType, LocalDate effectiveDate);
}
