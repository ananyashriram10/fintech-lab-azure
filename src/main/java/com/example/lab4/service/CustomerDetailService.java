package com.example.lab4.service;

import com.example.lab4.entity.CustomerDetail;
import java.util.List;

public interface CustomerDetailService {
    CustomerDetail create(CustomerDetail customerDetail);
    CustomerDetail update(Long id, CustomerDetail customerDetail);
    CustomerDetail getById(Long id);
    List<CustomerDetail> getAll();
    void delete(Long id);
}
