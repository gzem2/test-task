package com.haulmont.testtask.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Class that corresponds to CreditOffer table in database
 */
@Entity
@Table(name = "CreditOffer")
public class CreditOffer {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditId", referencedColumnName = "id")
    private Credit credit;

    @Column(name = "creditSum")
    private BigDecimal creditSum;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paymentLogId", referencedColumnName = "id")
    private PaymentLog paymentLog;

    public CreditOffer() {
    }

    public CreditOffer(Customer customer, Credit credit, BigDecimal creditSum, PaymentLog paymentLog) {
        this.customer = customer;
        this.credit = credit;
        this.creditSum = creditSum;
        this.paymentLog = paymentLog;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Credit getCredit() {
        return this.credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public BigDecimal getCreditSum() {
        return this.creditSum;
    }

    public void setCreditSum(BigDecimal creditSum) {
        this.creditSum = creditSum;
    }

    public PaymentLog getPaymentLog() {
        return this.paymentLog;
    }

    public void setPaymentLog(PaymentLog paymentLog) {
        this.paymentLog = paymentLog;
    }
}