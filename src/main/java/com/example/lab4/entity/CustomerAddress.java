package com.example.lab4.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer_address")
public class CustomerAddress extends BaseAuditEntity{

    @EmbeddedId
    private CustomerAddressId id;

    @MapsId("customerIdentifier")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_identifier", referencedColumnName = "customer_identifier")
    private CustomerDetail customer;

    @Column(name = "customer_address_value")
    private String customerAddressValue;

    public CustomerAddress() {}

    public CustomerAddressId getId() { return id; }
    public void setId(CustomerAddressId id) { this.id = id; }

    public CustomerDetail getCustomer() { return customer; }
    public void setCustomer(CustomerDetail customer) { this.customer = customer; }

    public String getCustomerAddressValue() { return customerAddressValue; }
    public void setCustomerAddressValue(String customerAddressValue) { this.customerAddressValue = customerAddressValue; }

    @Embeddable
    public static class CustomerAddressId implements Serializable {
        @Column(name = "customer_identifier")
        private Long customerIdentifier;

        @Column(name = "customer_address_type")
        private String customerAddressType;

        @Column(name = "effective_date")
        private LocalDate effectiveDate;

        public CustomerAddressId() {}

        public CustomerAddressId(Long customerIdentifier, String customerAddressType, LocalDate effectiveDate) {
            this.customerIdentifier = customerIdentifier;
            this.customerAddressType = customerAddressType;
            this.effectiveDate = effectiveDate;
        }

        public Long getCustomerIdentifier() { return customerIdentifier; }
        public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

        public String getCustomerAddressType() { return customerAddressType; }
        public void setCustomerAddressType(String customerAddressType) { this.customerAddressType = customerAddressType; }

        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerAddressId that)) return false;
            return Objects.equals(customerIdentifier, that.customerIdentifier)
                    && Objects.equals(customerAddressType, that.customerAddressType)
                    && Objects.equals(effectiveDate, that.effectiveDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerIdentifier, customerAddressType, effectiveDate);
        }
    }
}
