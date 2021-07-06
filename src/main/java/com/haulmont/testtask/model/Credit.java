package com.haulmont.testtask.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Class that corresponds to Credit table in database
 */
@Entity
@Table(name = "Credit")
public class Credit {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "creditLimit")
    private BigDecimal creditLimit;

    @Column(name = "interestRate")
    private Double interestRate;

    @OneToMany(mappedBy = "credit", orphanRemoval = true)
    private Set<CreditOffer> creditOffers;

    public Credit() {
    }

    public Credit(BigDecimal creditLimit, Double interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getCreditLimit() {
        if (this.creditLimit != null) {
            return new BigDecimal(this.creditLimit.stripTrailingZeros().toPlainString());
        } else {
            return this.creditLimit;
        }
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Set<CreditOffer> getCreditOffers() {
        return this.creditOffers;
    }

    public void setCreditOffers(Set<CreditOffer> creditOffer) {
        this.creditOffers = creditOffer;
    }
}