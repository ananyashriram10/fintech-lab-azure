package com.example.lab4.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_detail")
public class CustomerDetail extends BaseAuditEntity{

    @Id
    @Column(name = "customer_identifier")
    private Long customerIdentifier;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @Column(name = "customer_gender")
    private String customerGender;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "customer_date_of_birth")
    private LocalDate customerDateOfBirth;

    @Column(name = "customer_preferred_language")
    private String customerPreferredLanguage;

    @Column(name = "customer_status")
    private String customerStatus;

    @Column(name = "customer_country_of_origin")
    private String customerCountryOfOrigin;

    public CustomerDetail() {}

    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

    public String getCustomerFullName() { return customerFullName; }
    public void setCustomerFullName(String customerFullName) { this.customerFullName = customerFullName; }

    public String getCustomerGender() { return customerGender; }
    public void setCustomerGender(String customerGender) { this.customerGender = customerGender; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public LocalDate getCustomerDateOfBirth() { return customerDateOfBirth; }
    public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) { this.customerDateOfBirth = customerDateOfBirth; }

    public String getCustomerPreferredLanguage() { return customerPreferredLanguage; }
    public void setCustomerPreferredLanguage(String customerPreferredLanguage) { this.customerPreferredLanguage = customerPreferredLanguage; }

    public String getCustomerStatus() { return customerStatus; }
    public void setCustomerStatus(String customerStatus) { this.customerStatus = customerStatus; }

    public String getCustomerCountryOfOrigin() { return customerCountryOfOrigin; }
    public void setCustomerCountryOfOrigin(String customerCountryOfOrigin) { this.customerCountryOfOrigin = customerCountryOfOrigin; }
}
