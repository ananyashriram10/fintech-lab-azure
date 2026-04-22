package com.example.lab4.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer_identification")
public class CustomerIdentification extends BaseAuditEntity{

    @EmbeddedId
    private CustomerIdentificationId id;

    @MapsId("customerIdentifier")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_identifier", referencedColumnName = "customer_identifier")
    private CustomerDetail customer;

    @Column(name = "customer_identification_value")
    private String customerIdentificationValue;

    public CustomerIdentification() {}

    public CustomerIdentificationId getId() { return id; }
    public void setId(CustomerIdentificationId id) { this.id = id; }

    public CustomerDetail getCustomer() { return customer; }
    public void setCustomer(CustomerDetail customer) { this.customer = customer; }

    public String getCustomerIdentificationValue() { return customerIdentificationValue; }
    public void setCustomerIdentificationValue(String customerIdentificationValue) { this.customerIdentificationValue = customerIdentificationValue; }

    public Long getCustomerIdentifier() { return id != null ? id.customerIdentifier : null; }
    public String getCustomerIdentificationType() { return id != null ? id.customerIdentificationType : null; }
    public LocalDate getEffectiveDate() { return id != null ? id.effectiveDate : null; }

    @Embeddable
    public static class CustomerIdentificationId implements Serializable {
        @Column(name = "customer_identifier")
        private Long customerIdentifier;

        @Column(name = "customer_identification_type")
        private String customerIdentificationType;

        @Column(name = "effective_date")
        private LocalDate effectiveDate;

        public CustomerIdentificationId() {}

        public CustomerIdentificationId(Long customerIdentifier, String customerIdentificationType, LocalDate effectiveDate) {
            this.customerIdentifier = customerIdentifier;
            this.customerIdentificationType = customerIdentificationType;
            this.effectiveDate = effectiveDate;
        }

        public Long getCustomerIdentifier() { return customerIdentifier; }
        public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

        public String getCustomerIdentificationType() { return customerIdentificationType; }
        public void setCustomerIdentificationType(String customerIdentificationType) { this.customerIdentificationType = customerIdentificationType; }

        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerIdentificationId that)) return false;
            return Objects.equals(customerIdentifier, that.customerIdentifier)
                    && Objects.equals(customerIdentificationType, that.customerIdentificationType)
                    && Objects.equals(effectiveDate, that.effectiveDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerIdentifier, customerIdentificationType, effectiveDate);
        }
    }
}
