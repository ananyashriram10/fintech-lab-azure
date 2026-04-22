package com.example.lab4.controller;

import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.service.CustomerDetailService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-detail")
public class CustomerDetailController {

    private final CustomerDetailService service;

    public CustomerDetailController(CustomerDetailService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDetail create(@RequestBody CustomerDetail customerDetail) {
        return service.create(customerDetail);
    }

    @PutMapping("/{id}")
    public CustomerDetail update(@PathVariable Long id,
                                 @RequestBody CustomerDetail customerDetail) {
        return service.update(id, customerDetail);
    }

    @GetMapping("/{id}")
    public CustomerDetail getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CustomerDetail> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PostMapping("/generateCustomerDetails")
    public String generateCustomerDetails(@RequestParam int count) {

        Random random = new Random();

        String[] firstNames = {"Aarav", "Vivaan", "Aditya", "Vihaan", "Arjun", "Sai", "Reyansh", "Ayaan", "Krishna", "Ishaan",
                "Ananya", "Diya", "Myra", "Sara", "Aadhya", "Isha", "Kiara", "Riya", "Priya", "Neha"};
        String[] lastNames = {"Sharma", "Verma", "Gupta", "Singh", "Kumar", "Patel", "Reddy", "Nair", "Joshi", "Mehta",
                "Iyer", "Rao", "Das", "Chatterjee", "Banerjee", "Pillai", "Menon", "Deshmukh", "Kulkarni", "Patil"};
        String[] genders = {"Male", "Female", "Other"};
        String[] types = {"Normal", "Premium", "VIP"};
        String[] languages = {"English", "Hindi", "Tamil", "Telugu", "Kannada", "Malayalam", "Bengali", "Marathi"};
        String[] statuses = {"Active", "Inactive", "Suspended"};
        String[] countries = {"India", "USA", "UK", "Canada", "Australia", "Germany", "Japan", "Singapore"};

        for (int i = 0; i < count; i++) {

            CustomerDetail c = new CustomerDetail();

            String fullName = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
            int birthYear = 1960 + random.nextInt(46); // 1960-2005
            int birthMonth = 1 + random.nextInt(12);
            int birthDay = 1 + random.nextInt(28);

            c.setCustomerIdentifier((long) (i + 1));
            c.setCustomerFullName(fullName);
            c.setCustomerGender(genders[random.nextInt(genders.length)]);
            c.setCustomerType(types[random.nextInt(types.length)]);
            c.setCustomerDateOfBirth(LocalDate.of(birthYear, birthMonth, birthDay));
            c.setCustomerPreferredLanguage(languages[random.nextInt(languages.length)]);
            c.setCustomerStatus(statuses[random.nextInt(statuses.length)]);
            c.setCustomerCountryOfOrigin(countries[random.nextInt(countries.length)]);

            service.create(c);
        }

        return "Customer Details Generated";
    }
    
    }
