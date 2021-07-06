package com.haulmont.testtask.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.haulmont.testtask.dao.PaymentDao;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentService")
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;

    @Transactional
    public List<Payment> getAllPayments() {
        return paymentDao.findAll();
    }

    @Transactional List<Payment> getAllPaymentsOrderByPaymentNum() {
        return paymentDao.findAllByOrderByPaymentNumDesc();
    }

    @Transactional
    public Optional<Payment> getPayment(UUID id) {
        return paymentDao.findById(id);
    }

    @Transactional
    public void savePayment(Payment credit) {
        paymentDao.save(credit);
    }

    @Transactional
    public void deletePayment(UUID id) {
        paymentDao.deleteById(id);
    }

    @Transactional
    public List<Payment> findPaymentsByCreditOfferId(UUID id) {
        return paymentDao.findByCreditOfferId(id);
    }

    @Transactional
    public BigDecimal getDefaultPaymentByCreditOffer(CreditOffer creditOffer) {
        Credit credit = creditOffer.getCredit();
        Integer monthTerms = creditOffer.getMonthsTerm();

        Double interest = credit.getInterestRate() / 100 / 12;
        BigDecimal creditSum = creditOffer.getCreditSum();

        Double x = Math.pow(1 + interest, monthTerms);

        Double payment = (creditSum.intValue() * x * interest) / (x - 1);

        BigDecimal s = new BigDecimal(payment);
        return new BigDecimal(s.stripTrailingZeros().toPlainString()).setScale(0, RoundingMode.UP);
    }

    @Transactional
    public BigDecimal getDefaultPaymentByValues(Integer monthTerms, Double interest, BigDecimal creditSum) {
        interest = interest / 100 /12;
        Double x = Math.pow(1 + interest, monthTerms);

        Double payment = (creditSum.intValue() * x * interest) / (x - 1);
        
        BigDecimal s = new BigDecimal(payment);
        return new BigDecimal(s.stripTrailingZeros().toPlainString()).setScale(0, RoundingMode.UP);
    }
}