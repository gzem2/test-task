package com.haulmont.testtask.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditOfferService;
import com.haulmont.testtask.service.CreditService;
import com.haulmont.testtask.service.CustomerService;
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

    @GetMapping("/creditOffer/new")
    public ModelAndView showNewCreditOfferForm(CreditOffer creditOffer, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("creditOffer/creditOffer-form");

        Bank creditBank = bankService.getBank(bank).orElseGet(() -> new Bank());

        modelAndView.addObject("creditOffer", creditOffer);
        modelAndView.addObject("bank", creditBank);
        return modelAndView;
    }

    @GetMapping("/creditOffer/edit/{id}")
    public ModelAndView showEditCreditOfferForm(@PathVariable UUID id, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("credit/credit-form");
        CreditOffer creditForEdit = creditOfferService.getCreditOffer(id).orElseGet(() -> new CreditOffer());
        modelAndView.addObject("credit", creditForEdit);
        modelAndView.addObject("bank", bankService.getBank(bank).orElseGet(() -> new Bank()));
        return modelAndView;
    }

    @PostMapping("/creditOffer/new")
    public ModelAndView saveCreditOffer(@RequestParam Map<String, String> postBody, @RequestParam Optional<UUID> bank) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<CreditOffer> optEditCreditOffer = creditOfferService.getCreditOffer(id);

            if (optEditCreditOffer.isPresent()) {
                CreditOffer editCreditOffer = optEditCreditOffer.get();
                creditOfferService.saveCreditOffer(editCreditOffer);
            }
        } else {
            Customer cufco = customerService.getCustomer(UUID.fromString(postBody.get("customer")))
                    .orElseGet(() -> new Customer());
            Credit crfco = creditService.getCredit(UUID.fromString(postBody.get("credit")))
                    .orElseGet(() -> new Credit());
            CreditOffer newCreditOffer = new CreditOffer(cufco, crfco, new BigDecimal(postBody.get("creditSum")),
                    new ArrayList<>());
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
        if(optCO.isPresent()) {
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