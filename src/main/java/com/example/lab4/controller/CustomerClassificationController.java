package com.example.lab4.controller;

import com.example.lab4.entity.CustomerClassification;
import com.example.lab4.service.CustomerClassificationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/customer-classification")
public class CustomerClassificationController {

    private final CustomerClassificationService service;

    public CustomerClassificationController(CustomerClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerClassification create(@RequestBody CustomerClassification customerClassification) {
        return service.create(customerClassification);
    }

    @PutMapping("/{id}")
    public CustomerClassification update(@PathVariable Long id,
                                         @RequestBody CustomerClassification customerClassification) {
        return service.update(id, customerClassification);
    }

    @GetMapping("/{id}")
    public CustomerClassification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CustomerClassification> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PostMapping("/generateClassification")
    public String generateClassification(@RequestParam int count) {

        Random random = new Random();

        String[] classTypes = {"RISK", "INCOME", "SEGMENT", "LOYALTY", "CREDIT"};
        String[][] classValues = {
                {"LOW", "MEDIUM", "HIGH", "CRITICAL"},              // RISK
                {"BELOW_5L", "5L_10L", "10L_25L", "ABOVE_25L"},    // INCOME
                {"RETAIL", "CORPORATE", "HNI", "UHNI"},            // SEGMENT
                {"BRONZE", "SILVER", "GOLD", "PLATINUM"},           // LOYALTY
                {"AAA", "AA", "A", "BBB", "BB", "B"}               // CREDIT
        };

        for (int i = 0; i < count; i++) {

            int typeIndex = random.nextInt(classTypes.length);

            CustomerClassification c = new CustomerClassification();

            c.setCustomerClassificationId((long) (i + 1));
            c.setCustomerClassificationType(classTypes[typeIndex]);
            c.setCustomerClassificationValue(classValues[typeIndex][random.nextInt(classValues[typeIndex].length)]);
            c.setEffectiveDate(LocalDate.now().minusDays(random.nextInt(365)));

            service.create(c);
        }

        return "Customer Classification Generated";
    }
}
