package com.example.lab4.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_classification")
public class CustomerClassification extends BaseAuditEntity{

    @Id
    @Column(name = "customer_classification_id")
    private Long customerClassificationId;

    @Column(name = "customer_classification_type")
    private String customerClassificationType;

    @Column(name = "customer_classification_value")
    private String customerClassificationValue;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    public CustomerClassification() {}

    public Long getCustomerClassificationId() { return customerClassificationId; }
    public void setCustomerClassificationId(Long customerClassificationId) { this.customerClassificationId = customerClassificationId; }

    public String getCustomerClassificationType() { return customerClassificationType; }
    public void setCustomerClassificationType(String customerClassificationType) { this.customerClassificationType = customerClassificationType; }

    public String getCustomerClassificationValue() { return customerClassificationValue; }
    public void setCustomerClassificationValue(String customerClassificationValue) { this.customerClassificationValue = customerClassificationValue; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }
}
