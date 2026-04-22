package com.example.lab4.controller;

import com.example.lab4.entity.CustomerContactInformation;
import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.service.CustomerContactInformationService;
import com.example.lab4.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-contact")
public class CustomerContactInformationController {

    private final CustomerContactInformationService service;

    @Autowired
    private CustomerDetailService customerDetailService;

    public CustomerContactInformationController(CustomerContactInformationService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerContactInformation create(@RequestBody CustomerContactInformation customerContactInformation) {
        return service.create(customerContactInformation);
    }

    @GetMapping("/{customerId}/{type}/{effectiveDate}")
    public CustomerContactInformation getById(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        return service.getById(customerId, type, effectiveDate);
    }

    @GetMapping
    public List<CustomerContactInformation> getAll() {
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
    @PutMapping("/{customerId}/{contactType}/{effectiveDate}")
    public CustomerContactInformation update(
            @PathVariable Long customerId,
            @PathVariable String contactType,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate,
            @RequestBody CustomerContactInformation updated) {

        return service.update(customerId, contactType, effectiveDate, updated);
    }
    @PostMapping("/generateContactInformation")
    public String generateContactInformation(@RequestParam int count) {

        Random random = new Random();
        String[] types = {"EMAIL", "PHONE", "FAX"};
        String[] emailDomains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com"};

        for (int i = 0; i < count; i++) {

            String contactType = types[random.nextInt(types.length)];

            CustomerContactInformation contact = new CustomerContactInformation();

            CustomerContactInformation.CustomerContactInformationId id =
                    new CustomerContactInformation.CustomerContactInformationId(
                            (long) (i + 1),
                            contactType,
                            LocalDate.now()
                    );

            contact.setId(id);

            // Set customer reference
            CustomerDetail customer = customerDetailService.getById((long) (i + 1));
            contact.setCustomer(customer);

            // Set value based on type
            String contactValue;
            switch (contactType) {
                case "EMAIL":
                    String name = customer.getCustomerFullName().toLowerCase().replace(" ", ".");
                    contactValue = name + (random.nextInt(99) + 1) + "@" + emailDomains[random.nextInt(emailDomains.length)];
                    break;
                case "PHONE":
                    contactValue = "+91" + (7000000000L + random.nextInt(999999999));
                    break;
                default:
                    contactValue = "+91" + (1100000000 + random.nextInt(99999999));
                    break;
            }
            contact.setCustomerContactValue(contactValue);

            int startOffset = random.nextInt(365 * 3);
            contact.setStartDate(LocalDate.now().minusDays(startOffset));
            contact.setEndDate(LocalDate.now().minusDays(startOffset).plusYears(5 + random.nextInt(6)));

            service.create(contact);
        }

        return "Customer Contact Information Generated";
    }

}
