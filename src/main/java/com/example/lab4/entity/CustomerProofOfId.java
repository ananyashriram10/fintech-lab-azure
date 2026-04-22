package com.example.lab4.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer_proof_of_id")
public class CustomerProofOfId extends BaseAuditEntity{

    @EmbeddedId
    private CustomerProofOfIdId id;

    @MapsId("customerIdentifier")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_identifier", referencedColumnName = "customer_identifier")
    private CustomerDetail customer;

    @Column(name = "customer_proof_of_id_value")
    private String customerProofOfIdValue;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public CustomerProofOfId() {}

    public CustomerProofOfIdId getId() { return id; }
    public void setId(CustomerProofOfIdId id) { this.id = id; }

    public CustomerDetail getCustomer() { return customer; }
    public void setCustomer(CustomerDetail customer) { this.customer = customer; }

    public String getCustomerProofOfIdValue() { return customerProofOfIdValue; }
    public void setCustomerProofOfIdValue(String customerProofOfIdValue) { this.customerProofOfIdValue = customerProofOfIdValue; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Embeddable
    public static class CustomerProofOfIdId implements Serializable {
        @Column(name = "customer_identifier")
        private Long customerIdentifier;

        @Column(name = "customer_proof_of_id_type")
        private String customerProofOfIdType;

        @Column(name = "effective_date")
        private LocalDate effectiveDate;

        public CustomerProofOfIdId() {}

        public CustomerProofOfIdId(Long customerIdentifier, String customerProofOfIdType, LocalDate effectiveDate) {
            this.customerIdentifier = customerIdentifier;
            this.customerProofOfIdType = customerProofOfIdType;
            this.effectiveDate = effectiveDate;
        }

        public Long getCustomerIdentifier() { return customerIdentifier; }
        public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

        public String getCustomerProofOfIdType() { return customerProofOfIdType; }
        public void setCustomerProofOfIdType(String customerProofOfIdType) { this.customerProofOfIdType = customerProofOfIdType; }

        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerProofOfIdId that)) return false;
            return Objects.equals(customerIdentifier, that.customerIdentifier)
                    && Objects.equals(customerProofOfIdType, that.customerProofOfIdType)
                    && Objects.equals(effectiveDate, that.effectiveDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerIdentifier, customerProofOfIdType, effectiveDate);
        }
    }
}
