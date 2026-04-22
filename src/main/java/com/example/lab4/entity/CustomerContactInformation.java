package com.example.lab4.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer_contact_information")
public class CustomerContactInformation extends BaseAuditEntity{

    @EmbeddedId
    private CustomerContactInformationId id;

    @MapsId("customerIdentifier")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_identifier", referencedColumnName = "customer_identifier")
    private CustomerDetail customer;

    @Column(name = "customer_contact_value")
    private String customerContactValue;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public CustomerContactInformation() {}

    public CustomerContactInformationId getId() { return id; }
    public void setId(CustomerContactInformationId id) { this.id = id; }

    public CustomerDetail getCustomer() { return customer; }
    public void setCustomer(CustomerDetail customer) { this.customer = customer; }

    public String getCustomerContactValue() { return customerContactValue; }
    public void setCustomerContactValue(String customerContactValue) { this.customerContactValue = customerContactValue; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Embeddable
    public static class CustomerContactInformationId implements Serializable {
        @Column(name = "customer_identifier")
        private Long customerIdentifier;

        @Column(name = "customer_contact_type")
        private String customerContactType;

        @Column(name = "effective_date")
        private LocalDate effectiveDate;

        public CustomerContactInformationId() {}

        public CustomerContactInformationId(Long customerIdentifier, String customerContactType, LocalDate effectiveDate) {
            this.customerIdentifier = customerIdentifier;
            this.customerContactType = customerContactType;
            this.effectiveDate = effectiveDate;
        }

        public Long getCustomerIdentifier() { return customerIdentifier; }
        public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

        public String getCustomerContactType() { return customerContactType; }
        public void setCustomerContactType(String customerContactType) { this.customerContactType = customerContactType; }

        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerContactInformationId that)) return false;
            return Objects.equals(customerIdentifier, that.customerIdentifier)
                    && Objects.equals(customerContactType, that.customerContactType)
                    && Objects.equals(effectiveDate, that.effectiveDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerIdentifier, customerContactType, effectiveDate);
        }
    }
}
