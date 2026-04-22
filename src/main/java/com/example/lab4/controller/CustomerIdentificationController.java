package com.example.lab4.controller;

import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.entity.CustomerIdentification;
import com.example.lab4.service.CustomerDetailService;
import com.example.lab4.service.CustomerIdentificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-identification")
public class CustomerIdentificationController {

    private final CustomerIdentificationService service;
    
    @Autowired
    private CustomerDetailService customerDetailService;

    public CustomerIdentificationController(CustomerIdentificationService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerIdentification create(@RequestBody CustomerIdentification customerIdentification) {
        return service.create(customerIdentification);
    }

    @GetMapping("/{customerId}/{type}/{effectiveDate}")
    public CustomerIdentification getById(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        return service.getById(customerId, type, effectiveDate);
    }

    @GetMapping
    public List<CustomerIdentification> getAll() {
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
    public CustomerIdentification update(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate,
            @RequestBody CustomerIdentification updated) {

        return service.update(customerId, type, effectiveDate, updated);
    }
    
    @PostMapping("/generateIdentification")
    public String generateIdentification(@RequestParam int count) {

        Random random = new Random();

        String[] idTypes = {"AADHAR", "PAN", "PASSPORT", "DRIVING_LICENSE", "VOTER_ID"};

        for (int i = 0; i < count; i++) {

            String idType = idTypes[random.nextInt(idTypes.length)];

            // 1. Create composite ID
            CustomerIdentification.CustomerIdentificationId id =
                    new CustomerIdentification.CustomerIdentificationId(
                            (long) (i + 1),     // customerIdentifier
                            idType,             // identification type
                            LocalDate.now()     // effective date
                    );

            // 2. Create object
            CustomerIdentification identification = new CustomerIdentification();
            identification.setId(id);

            // 3. Set customer (VERY IMPORTANT)
            CustomerDetail customer = customerDetailService.getById((long) (i + 1));
            identification.setCustomer(customer);

            // 4. Set value
            String idValue;
            switch (idType) {
                case "PAN": idValue = "" + (char)('A' + random.nextInt(26)) + (char)('A' + random.nextInt(26))
                        + "P" + (char)('A' + random.nextInt(26)) + (1000 + random.nextInt(9000))
                        + (char)('A' + random.nextInt(26)); break;
                case "PASSPORT": idValue = "" + (char)('A' + random.nextInt(26)) + (1000000 + random.nextInt(9000000)); break;
                case "AADHAR": idValue = String.valueOf(100000000000L + (long)(random.nextDouble() * 899999999999L)); break;
                default: idValue = idType.substring(0, 2) + (100000 + random.nextInt(900000)); break;
            }
            identification.setCustomerIdentificationValue(idValue);

            // 5. Save
            service.create(identification);
        }

        return "Customer Identification Generated";
    }

}
