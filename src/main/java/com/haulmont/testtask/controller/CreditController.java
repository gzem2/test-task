package com.haulmont.testtask.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreditController {
    @Autowired
    CreditService creditService;

    @Autowired
    BankService bankService;

    @GetMapping("/credit/new")
    public ModelAndView showNewCreditForm(Credit credit, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("credit/credit-form");
        modelAndView.addObject("credit", credit);
        modelAndView.addObject("bank", bankService.getBank(bank).orElseGet(() -> new Bank()));
        return modelAndView;
    }

    @GetMapping("/credit/edit/{id}")
    public ModelAndView showEditCreditForm(@PathVariable UUID id, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("credit/credit-form");
        Credit creditForEdit = creditService.getCredit(id).orElseGet(() -> new Credit());
        modelAndView.addObject("credit", creditForEdit);
        modelAndView.addObject("bank", bankService.getBank(bank).orElseGet(() -> new Bank()));
        return modelAndView;
    }

    @PostMapping("/credit/new")
    public ModelAndView saveCredit(@RequestParam Map<String, String> postBody, @RequestParam Optional<UUID> bank) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<Credit> optEditCredit = creditService.getCredit(id);

            if (optEditCredit.isPresent()) {
                Credit editCredit = optEditCredit.get();
                editCredit.setCreditLimit(new BigDecimal(postBody.get("creditLimit")));
                editCredit.setInterestRate(Double.valueOf(postBody.get("interestRate")));
                creditService.saveCredit(editCredit);
            }
        } else {
            Credit newCredit = new Credit(new BigDecimal(postBody.get("creditLimit")),
                    Double.valueOf(postBody.get("interestRate")));
            creditService.saveCredit(newCredit);
            if (bank.isPresent()) {
                Optional<Bank> optBank = bankService.getBank(bank.get());
                if (optBank.isPresent()) {
                    bankService.addCreditToBank(optBank.get(), newCredit);
                }
            }
        }

        if (bank.isPresent()) {
            return new ModelAndView("redirect:/bank/" + bank.get());
        } else {
            return new ModelAndView("redirect:/banks");
        }
    }

    @GetMapping("/credit/delete/{id}")
    public ModelAndView deleteCredit(@PathVariable UUID id, @RequestParam Optional<UUID> bank) {
        Set<Bank> banksWithCredit = bankService.getBanksForCreditId(id);
        Optional<Credit> optCredit = creditService.getCredit(id);
        if (optCredit.isPresent()) {
            for (Bank b : banksWithCredit) {
                bankService.removeCreditFromBank(b, optCredit.get());
            }
        }

        creditService.deleteCredit(id);

        if (bank.isPresent()) {
            return new ModelAndView("redirect:/bank/" + bank.get());
        } else {
            return new ModelAndView("redirect:/banks");
        }
    }
}