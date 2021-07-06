package com.haulmont.testtask.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditOfferService;
import com.haulmont.testtask.service.CreditService;
import com.haulmont.testtask.service.CustomerService;
import com.haulmont.testtask.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    CreditOfferService creditOfferService;

    @Autowired
    BankService bankService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CreditService creditService;

    @GetMapping("/payment/new")
    public ModelAndView showNewPaymentForm(Payment payment, @RequestParam UUID creditOffer) {
        ModelAndView modelAndView = new ModelAndView("payment/payment-form");

        CreditOffer co = creditOfferService.getCreditOffer(creditOffer).orElseGet(() -> new CreditOffer());

        modelAndView.addObject("payment", payment);
        modelAndView.addObject("creditOffer", co);
        modelAndView.addObject("monthlyPayment", paymentService.getDefaultPaymentByCreditOffer(co));
        return modelAndView;
    }

    @PostMapping("/payment/new")
    public ModelAndView savePayment(@RequestParam Map<String, String> postBody, @RequestParam UUID creditOffer) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<Payment> optPayment = paymentService.getPayment(id);

            if (optPayment.isPresent()) {
                Payment editPayment = optPayment.get();
                editPayment.setDateOfPayment(LocalDate.parse(postBody.get("dateOfPayment")));
                editPayment.setPayment(new BigDecimal(postBody.get("payment")));
                editPayment.setMainDebt(new BigDecimal(postBody.get("mainDebt")));
                editPayment.setInterestDebt(new BigDecimal(postBody.get("interestDebt")));
                paymentService.savePayment(editPayment);
            }
        } else {
            Optional<CreditOffer> coForPayment = creditOfferService.getCreditOffer(creditOffer);
            if (coForPayment.isPresent()) {
                CreditOffer co = coForPayment.get();
                BigDecimal md = new BigDecimal(postBody.get("mainDebt"));
                BigDecimal id = new BigDecimal(postBody.get("interestDebt"));

                List<Payment> paymentsForCreditOffer = paymentService.findPaymentsByCreditOfferId(co.getId());

                Integer paymentNum = paymentsForCreditOffer.size() + 1;
                Payment newPayment = new Payment(LocalDate.parse(postBody.get("dateOfPayment")), paymentNum,
                        new BigDecimal(postBody.get("payment")), md, id, coForPayment.get());
                paymentService.savePayment(newPayment);

                co.setMainDebt(co.getMainDebt().subtract(md));
                co.setInterestDebt(co.getInterestDebt().subtract(id));
                creditOfferService.saveCreditOffer(co);
            }
        }

        return new ModelAndView("redirect:/creditOffer/" + creditOffer);
    }

    @GetMapping("/payment/delete/{id}")
    public ModelAndView deletePayment(@PathVariable UUID id, @RequestParam Optional<UUID> creditOffer) {
        paymentService.deletePayment(id);

        if (creditOffer.isPresent()) {
            return new ModelAndView("redirect:/creditOffer/" + creditOffer.get());
        } else {
            return new ModelAndView("redirect:/banks");
        }
    }
}