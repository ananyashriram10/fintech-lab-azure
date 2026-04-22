package com.example.lab4.service;

import com.example.lab4.entity.CustomerProofOfId;
import java.time.LocalDate;
import java.util.List;

public interface CustomerProofOfIdService {

    CustomerProofOfId create(CustomerProofOfId customerProofOfId);

    CustomerProofOfId getById(Long customerId,
                              String proofType,
                              LocalDate effectiveDate);

    List<CustomerProofOfId> getAll();

    CustomerProofOfId update(Long customerId,
                             String proofType,
                             LocalDate effectiveDate,
                             CustomerProofOfId customerProofOfId);

    void delete(Long customerId,
                String proofType,
                LocalDate effectiveDate);
}
