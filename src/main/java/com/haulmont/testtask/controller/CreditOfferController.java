package com.haulmont.testtask.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Customer;
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
public class CreditOfferController {
    @Autowired
    CreditOfferService creditOfferService;

    @Autowired
    BankService bankService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CreditService creditService;

    @Autowired
    PaymentService paymentService;

    @GetMapping("/creditOffer/new")
    public ModelAndView showNewCreditOfferForm(CreditOffer creditOffer, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("creditOffer/creditOffer-form");

        Bank creditBank = bankService.getBank(bank).orElseGet(() -> new Bank());

        modelAndView.addObject("creditOffer", creditOffer);
        modelAndView.addObject("bank", creditBank);
        return modelAndView;
    }

    @GetMapping("/creditOffer/{id}")
    public ModelAndView showBank(@PathVariable UUID id, @RequestParam Optional<UUID> bank) {
        ModelAndView modelAndView = new ModelAndView("creditOffer/creditOffer-details");

        if (bank.isPresent()) {
            modelAndView.addObject("bank", bankService.getBank(bank.get()).orElseGet(() -> new Bank()));
        } else {
            modelAndView.addObject("bank", new Bank());
        }

        CreditOffer co = creditOfferService.getCreditOffer(id).get();
        List<Payment> pl = co.getPaymentLog();

        pl.sort((o1, o2) -> o1.getPaymentNum().compareTo(o2.getPaymentNum()));
        modelAndView.addObject("payments", pl);
        modelAndView.addObject("creditOffer", co);
        return modelAndView;
    }

    @GetMapping("/creditOffer/edit/{id}")
    public ModelAndView showEditCreditOfferForm(@PathVariable UUID id, @RequestParam Optional<UUID> bank) {
        ModelAndView modelAndView = new ModelAndView("creditOffer/creditOffer-form");
        CreditOffer co = creditOfferService.getCreditOffer(id).get();
        modelAndView.addObject("creditOffer", co);
        if (bank.isPresent()) {
            modelAndView.addObject("bank", bankService.getBank(bank.get()).orElseGet(() -> new Bank()));
        } else {
            Set<Bank> banks = bankService.getBanksForCreditId(co.getCredit().getId());
            modelAndView.addObject("bank", banks.iterator().next());
        }
        return modelAndView;
    }

    @PostMapping("/creditOffer/new")
    public ModelAndView saveCreditOffer(@RequestParam Map<String, String> postBody, @RequestParam Optional<UUID> bank) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<CreditOffer> optEditCreditOffer = creditOfferService.getCreditOffer(id);
            Customer cufco = customerService.getCustomer(UUID.fromString(postBody.get("customer")))
                    .orElseGet(() -> new Customer());
            Credit crfco = creditService.getCredit(UUID.fromString(postBody.get("credit")))
                    .orElseGet(() -> new Credit());

            if (optEditCreditOffer.isPresent()) {
                CreditOffer editCreditOffer = optEditCreditOffer.get();
                editCreditOffer.setCustomer(cufco);
                editCreditOffer.setCredit(crfco);

                BigDecimal newSum = new BigDecimal(postBody.get("creditSum"));
                if (newSum.compareTo(crfco.getCreditLimit()) > 0) {
                    return new ModelAndView("shared/error", "message", "Сумма кредита превышает лимит");
                }

                editCreditOffer.setCreditSum(newSum);
                editCreditOffer.setStartDate(LocalDate.parse(postBody.get("startDate")));
                editCreditOffer.setMonthsTerm(Integer.valueOf(postBody.get("monthsTerm")));

                creditOfferService.saveCreditOffer(editCreditOffer);
            }
        } else {
            Customer cufco = customerService.getCustomer(UUID.fromString(postBody.get("customer")))
                    .orElseGet(() -> new Customer());
            Credit crfco = creditService.getCredit(UUID.fromString(postBody.get("credit")))
                    .orElseGet(() -> new Credit());

            BigDecimal newSum = new BigDecimal(postBody.get("creditSum"));
            if (newSum.compareTo(crfco.getCreditLimit()) > 0) {
                return new ModelAndView("shared/error", "message", "Сумма кредита превышает лимит");
            }

            Integer monthTerm = Integer.valueOf(postBody.get("monthsTerm"));
            BigDecimal mainDebt = new BigDecimal(postBody.get("creditSum"));

            BigDecimal creditSum = new BigDecimal(postBody.get("creditSum"));
            BigDecimal interestDebt = paymentService
                    .getDefaultPaymentByValues(monthTerm, crfco.getInterestRate(), creditSum)
                    .multiply(new BigDecimal(monthTerm)).subtract(creditSum);
            CreditOffer newCreditOffer = new CreditOffer(cufco, crfco, creditSum,
                    LocalDate.parse(postBody.get("startDate")), monthTerm, mainDebt, interestDebt, new ArrayList<>());
            creditOfferService.saveCreditOffer(newCreditOffer);
        }

        if (bank.isPresent()) {
            return new ModelAndView("redirect:/bank/" + bank.get());
        } else {
            return new ModelAndView("redirect:/banks");
        }
    }

    @GetMapping("/creditOffer/delete/{id}")
    public ModelAndView deleteCreditOffer(@PathVariable UUID id, @RequestParam Optional<UUID> bank) {
        Optional<CreditOffer> optCO = creditOfferService.getCreditOffer(id);
        if (optCO.isPresent()) {
            CreditOffer cO = optCO.get();
            creditService.removeCreditOfferFromCredit(cO, cO.getCredit());
            customerService.removeCreditOfferFromCustomer(cO, cO.getCustomer());
        }

        if (bank.isPresent()) {
            return new ModelAndView("redirect:/bank/" + bank.get());
        } else {
            return new ModelAndView("redirect:/banks");
        }
    }
}