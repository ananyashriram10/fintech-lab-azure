package com.example.lab4.controller;

import com.example.lab4.entity.CustomerAddress;
import com.example.lab4.entity.CustomerDetail;
import com.example.lab4.service.CustomerAddressService;
import com.example.lab4.service.CustomerDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api/customer-address")


public class CustomerAddressController {

    private final CustomerAddressService service;
    
    @Autowired
    private CustomerDetailService customerDetailService;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerAddress create(@RequestBody CustomerAddress customerAddress) {
        return service.create(customerAddress);
    }

    @GetMapping("/{customerId}/{type}/{effectiveDate}")
    public CustomerAddress getById(
            @PathVariable Long customerId,
            @PathVariable String type,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate) {

        return service.getById(customerId, type, effectiveDate);
    }

    @GetMapping
    public List<CustomerAddress> getAll() {
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
    @PutMapping("/{customerId}/{addressType}/{effectiveDate}")
    public CustomerAddress update(
            @PathVariable Long customerId,
            @PathVariable String addressType,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveDate,
            @RequestBody CustomerAddress updated) {

        return service.update(customerId, addressType, effectiveDate, updated);
    }
    
    @PostMapping("/generateAddress")
    public String generateAddress(@RequestParam int count) {

        Random random = new Random();

        String[] addressTypes = {"HOME", "WORK", "MAILING"};

        // Country-specific cities
        String[] indianCities = {"Mumbai", "Delhi", "Bangalore", "Chennai", "Kolkata", "Hyderabad", "Pune", "Jaipur"};
        String[] usCities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "San Francisco"};
        String[] ukCities = {"London", "Manchester", "Birmingham", "Edinburgh", "Liverpool", "Bristol"};
        String[] canadaCities = {"Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa", "Edmonton"};
        String[] australiaCities = {"Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide", "Canberra"};
        String[] germanyCities = {"Berlin", "Munich", "Hamburg", "Frankfurt", "Cologne", "Stuttgart"};
        String[] japanCities = {"Tokyo", "Osaka", "Kyoto", "Yokohama", "Nagoya", "Sapporo"};
        String[] singaporeCities = {"Orchard", "Marina Bay", "Jurong", "Tampines", "Woodlands", "Sentosa"};

        String[] streets = {"Main Street", "Park Avenue", "High Street", "Station Road", "Church Street",
                "Hill Road", "Lake Road", "Market Street", "Ring Road", "Garden Lane"};

        for (int i = 0; i < count; i++) {

            // 1. Fetch customer to get country
            CustomerDetail customer = customerDetailService.getById((long) (i + 1));
            String country = customer.getCustomerCountryOfOrigin();

            // Pick city based on customer's country
            String[] cities;
            switch (country) {
                case "USA": cities = usCities; break;
                case "UK": cities = ukCities; break;
                case "Canada": cities = canadaCities; break;
                case "Australia": cities = australiaCities; break;
                case "Germany": cities = germanyCities; break;
                case "Japan": cities = japanCities; break;
                case "Singapore": cities = singaporeCities; break;
                default: cities = indianCities; break;
            }

            String addressType = addressTypes[random.nextInt(addressTypes.length)];

            // 2. Create ID (composite key)
            CustomerAddress.CustomerAddressId id =
                    new CustomerAddress.CustomerAddressId(
                            (long) (i + 1),         // customerIdentifier
                            addressType,            // address type
                            LocalDate.now()         // effective date
                    );

            // 3. Create address object
            CustomerAddress address = new CustomerAddress();
            address.setId(id);
            address.setCustomer(customer);

            // 4. Set value — city matches customer's country
            String addressValue = (random.nextInt(500) + 1) + " " + streets[random.nextInt(streets.length)] + ", " + cities[random.nextInt(cities.length)] + ", " + country;
            address.setCustomerAddressValue(addressValue);

            // 5. Save
            service.create(address);
        }

        return "Customer Addresses Generated";
    }

}
