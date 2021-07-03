package com.haulmont.testtask.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public BigDecimal getLoanLimit() {
        return this.creditLimit;
    }

    public void setLoanLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}