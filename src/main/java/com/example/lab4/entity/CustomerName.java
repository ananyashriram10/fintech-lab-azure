package com.example.lab4.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer_name")
public class CustomerName extends BaseAuditEntity{

    @EmbeddedId
    private CustomerNameId id;

    @MapsId("customerIdentifier")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_identifier", referencedColumnName = "customer_identifier")
    private CustomerDetail customer;

    @Column(name = "customer_name_value")
    private String customerNameValue;

    public CustomerName() {}

    public CustomerNameId getId() { return id; }
    public void setId(CustomerNameId id) { this.id = id; }

    public CustomerDetail getCustomer() { return customer; }
    public void setCustomer(CustomerDetail customer) { this.customer = customer; }

    public String getCustomerNameValue() { return customerNameValue; }
    public void setCustomerNameValue(String customerNameValue) { this.customerNameValue = customerNameValue; }

    @Embeddable
    public static class CustomerNameId implements Serializable {
        @Column(name = "customer_identifier")
        private Long customerIdentifier;

        @Column(name = "customer_name_type")
        private String customerNameType;

        @Column(name = "effective_date")
        private LocalDate effectiveDate;

        public CustomerNameId() {}

        public CustomerNameId(Long customerIdentifier, String customerNameType, LocalDate effectiveDate) {
            this.customerIdentifier = customerIdentifier;
            this.customerNameType = customerNameType;
            this.effectiveDate = effectiveDate;
        }

        public Long getCustomerIdentifier() { return customerIdentifier; }
        public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

        public String getCustomerNameType() { return customerNameType; }
        public void setCustomerNameType(String customerNameType) { this.customerNameType = customerNameType; }

        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerNameId that)) return false;
            return Objects.equals(customerIdentifier, that.customerIdentifier)
                    && Objects.equals(customerNameType, that.customerNameType)
                    && Objects.equals(effectiveDate, that.effectiveDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerIdentifier, customerNameType, effectiveDate);
        }
    }
}
