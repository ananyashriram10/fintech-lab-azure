package com.example.lab4.service;

import com.example.lab4.entity.CustomerProofOfId;
import com.example.lab4.entity.CustomerProofOfId.CustomerProofOfIdId;
import com.example.lab4.repository.CustomerProofOfIdRepository;
import com.example.lab4.service.CustomerProofOfIdService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerProofOfIdServiceImpl
        implements CustomerProofOfIdService {

    private final CustomerProofOfIdRepository repository;

    public CustomerProofOfIdServiceImpl(
            CustomerProofOfIdRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerProofOfId create(CustomerProofOfId customerProofOfId) {
        return repository.save(customerProofOfId);
    }

    @Override
    public CustomerProofOfId getById(Long customerId,
                                     String type,
                                     LocalDate effectiveDate) {

        CustomerProofOfIdId id =
                new CustomerProofOfIdId(customerId, type, effectiveDate);

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proof of ID not found"));
    }

    @Override
    public List<CustomerProofOfId> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long customerId,
                       String type,
                       LocalDate effectiveDate) {

        CustomerProofOfIdId id =
                new CustomerProofOfIdId(customerId, type, effectiveDate);

        repository.deleteById(id);
    }

    @Override
    public CustomerProofOfId update(Long customerId,
                                    String type,
                                    LocalDate effectiveDate,
                                    CustomerProofOfId updated) {

        CustomerProofOfIdId id =
                new CustomerProofOfIdId(customerId, type, effectiveDate);

        CustomerProofOfId existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proof of ID not found"));

        existing.setCustomerProofOfIdValue(updated.getCustomerProofOfIdValue());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());

        return repository.save(existing);
    }
}
