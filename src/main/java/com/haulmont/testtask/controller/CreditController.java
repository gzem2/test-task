package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CreditService;
import com.haulmont.testtask.service.CreditService;
import com.haulmont.testtask.service.CustomerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping("/credit/new")
    public String showNewCreditForm(Credit credit) {
        return "credit/credit-form";
    }

    @GetMapping("/credit/edit/{id}")
    public ModelAndView showEditCreditForm(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("credit/credit-form");
        Credit creditForEdit = creditService.getCredit(id).orElseGet(() -> new Credit());
        modelAndView.addObject("credit", creditForEdit);
        return modelAndView;
    }

    @PostMapping("/credit/new")
    public ModelAndView saveCredit(@RequestParam Map<String, String> postBody) {
        String postId = postBody.get("id");
        if(postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<Credit> optEditCredit = creditService.getCredit(id);

            if (optEditCredit.isPresent()) {
                Credit editCredit = optEditCredit.get();
                editCredit.setCreditLimit(new BigDecimal(postBody.get("creditLimit")));
                editCredit.setInterestRate(Double.valueOf(postBody.get("interestRate")));
                creditService.saveCredit(editCredit);
            }
        } else {
            Credit newCredit = new Credit(new BigDecimal(postBody.get("creditLimit")), Double.valueOf(postBody.get("interestRate")));
            creditService.saveCredit(newCredit);
        }
        return new ModelAndView("redirect:/credits");
    }

    @GetMapping("/credit/delete/{id}")
    public ModelAndView deleteCredit(@PathVariable UUID id) {
        creditService.deleteCredit(id);
        return new ModelAndView("redirect:/credits");
    }
}