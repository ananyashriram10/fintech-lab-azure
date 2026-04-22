package com.example.lab4.controller;

import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.entity.CustomerName;
import com.example.lab4.service.CustomerDetailService;
import com.example.lab4.service.CustomerNameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-name")
public class CustomerNameController {

    private final CustomerNameService service;
    
    @Autowired
    private CustomerDetailService customerDetailService;

    public CustomerNameController(CustomerNameService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerName create(@RequestBody CustomerName customerName) {
        return service.create(customerName);
    }

    @GetMapping("/{customerId}/{nameType}/{effectiveDate}")
    public CustomerName getById(
            @PathVariable Long customerId,
            @PathVariable String nameType,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        return service.getById(customerId, nameType, effectiveDate);
    }

    @GetMapping
    public List<CustomerName> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{customerId}/{nameType}/{effectiveDate}")
    public void delete(
            @PathVariable Long customerId,
            @PathVariable String nameType,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        service.delete(customerId, nameType, effectiveDate);
    }
    @PutMapping("/{customerId}/{nameType}/{effectiveDate}")
    public CustomerName update(
            @PathVariable Long customerId,
            @PathVariable String nameType,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate,
            @RequestBody CustomerName updated) {

        return service.update(customerId, nameType, effectiveDate, updated);
    }
    
    @PostMapping("/generateName")
    public String generateName(@RequestParam int count) {

        String[] nameTypes = {"PRIMARY", "LEGAL", "PREFERRED", "MAIDEN", "ALIAS"};
        Random random = new Random();

        for (int i = 0; i < count; i++) {

            // 1. Fetch customer to derive name from customerFullName
            CustomerDetail customer = customerDetailService.getById((long) (i + 1));
            String fullName = customer.getCustomerFullName();

            // 2. Create composite ID — one row per customer
            CustomerName.CustomerNameId id =
                    new CustomerName.CustomerNameId(
                            (long) (i + 1),
                            nameTypes[random.nextInt(nameTypes.length)],
                            LocalDate.now()
                    );

            // 3. Create object
            CustomerName name = new CustomerName();
            name.setId(id);
            name.setCustomer(customer);
            name.setCustomerNameValue(fullName);

            // 4. Save
            service.create(name);
        }

        return "Customer Name Generated";
    }


}
