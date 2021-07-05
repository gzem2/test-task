package com.haulmont.testtask.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Class that corresponds to PaymentLog table in database
 */
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "dateOfPayment")
    private LocalDate dateOfPayment;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "mainDebt")
    private BigDecimal mainDebt;

    @Column(name = "interestDebt")
    private BigDecimal interestDebt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditOfferId", referencedColumnName = "id")
    private CreditOffer creditOffer;

    public Payment() {
    }

    public Payment(LocalDate dateOfPayment, BigDecimal payment, BigDecimal mainDebt, BigDecimal interestDebt) {
        this.dateOfPayment = dateOfPayment;
        this.payment = payment;
        this.mainDebt = mainDebt;
        this.interestDebt = interestDebt;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateOfPayment() {
        return this.dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getMainDebt() {
        return this.mainDebt;
    }

    public void setMainDebt(BigDecimal mainDebt) {
        this.mainDebt = mainDebt;
    }

    public BigDecimal getInterestDebt() {
        return this.interestDebt;
    }

    public void setInterestDebt(BigDecimal interestDebt) {
        this.interestDebt = interestDebt;
    }
}
