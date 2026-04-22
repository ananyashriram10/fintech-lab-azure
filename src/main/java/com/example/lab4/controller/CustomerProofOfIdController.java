package com.example.lab4.controller;

import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.entity.CustomerProofOfId;
import com.example.lab4.service.CustomerDetailService;
import com.example.lab4.service.CustomerProofOfIdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-proof")
public class CustomerProofOfIdController {

    private final CustomerProofOfIdService service;
    
    @Autowired
    private CustomerDetailService customerDetailService;

    public CustomerProofOfIdController(CustomerProofOfIdService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerProofOfId create(@RequestBody CustomerProofOfId customerProofOfId) {
        return service.create(customerProofOfId);
    }

    @GetMapping("/{customerId}/{type}/{effectiveDate}")
    public CustomerProofOfId getById(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        return service.getById(customerId, type, effectiveDate);
    }

    @GetMapping
    public List<CustomerProofOfId> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{customerId}/{type}/{effectiveDate}")
    public void delete(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        service.delete(customerId, type, effectiveDate);
    }
    @PutMapping("/{customerId}/{type}/{effectiveDate}")
    public CustomerProofOfId update(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate,
            @RequestBody CustomerProofOfId updated) {

        return service.update(customerId, type, effectiveDate, updated);
    }
    
    @PostMapping("/generateProofOfId")
    public String generateProofOfId(@RequestParam int count) {

        Random random = new Random();

        String[] proofTypes = {"AADHAR", "PASSPORT", "VOTER_ID", "DRIVING_LICENSE", "RATION_CARD"};

        for (int i = 0; i < count; i++) {

            String proofType = proofTypes[random.nextInt(proofTypes.length)];

            // 1. Create composite ID
            CustomerProofOfId.CustomerProofOfIdId id =
                    new CustomerProofOfId.CustomerProofOfIdId(
                            (long) (i + 1),     // customerIdentifier
                            proofType,          // proof type
                            LocalDate.now()     // effective date
                    );

            // 2. Create object
            CustomerProofOfId proof = new CustomerProofOfId();
            proof.setId(id);

            // 3. Set customer (VERY IMPORTANT)
            CustomerDetail customer = customerDetailService.getById((long) (i + 1));
            proof.setCustomer(customer);

            // 4. Set values
            proof.setCustomerProofOfIdValue(proofType + (100000 + random.nextInt(900000)));
            int startOffset = random.nextInt(365 * 5);
            proof.setStartDate(LocalDate.now().minusDays(startOffset));
            proof.setEndDate(LocalDate.now().minusDays(startOffset).plusYears(5 + random.nextInt(11)));

            // 5. Save
            service.create(proof);
        }

        return "Customer Proof Of ID Generated";
    }


}
