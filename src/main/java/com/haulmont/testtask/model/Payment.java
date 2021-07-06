package com.haulmont.testtask.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
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
    
    @Column(name = "paymentNum")
    private Integer paymentNum;

    @Column(name = "dateOfPayment")
    private LocalDate dateOfPayment;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "mainDebt")
    private BigDecimal mainDebt;

    @Column(name = "interestDebt")
    private BigDecimal interestDebt;

    @ManyToOne
    @JoinColumn(name = "creditOfferId", referencedColumnName = "id")
    private CreditOffer creditOffer;

    public Payment() {
    }

    public Payment(LocalDate dateOfPayment, Integer paymentNum, BigDecimal payment, BigDecimal mainDebt, BigDecimal interestDebt, CreditOffer creditOffer) {
        this.dateOfPayment = dateOfPayment;
        this.paymentNum = paymentNum;
        this.payment = payment;
        this.mainDebt = mainDebt;
        this.interestDebt = interestDebt;
        this.creditOffer = creditOffer;
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
        if (this.payment != null) {
            return new BigDecimal(this.payment.stripTrailingZeros().toPlainString());
        } else {
            return this.payment;
        }
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getMainDebt() {
        if (this.mainDebt != null) {
            return new BigDecimal(this.mainDebt.stripTrailingZeros().toPlainString());
        } else {
            return this.mainDebt;
        }
    }

    public void setMainDebt(BigDecimal mainDebt) {
        this.mainDebt = mainDebt;
    }

    public BigDecimal getInterestDebt() {
        if (this.interestDebt != null) {
            return new BigDecimal(this.interestDebt.stripTrailingZeros().toPlainString());
        } else {
            return this.interestDebt;
        }
    }

    public void setInterestDebt(BigDecimal interestDebt) {
        this.interestDebt = interestDebt;
    }
    
    public Integer getPaymentNum() {
        return this.paymentNum;
    }

    public void setPaymentNum(Integer paymentNum) {
        this.paymentNum = paymentNum;
    }

    public CreditOffer getCreditOffer() {
        return this.creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }
}
