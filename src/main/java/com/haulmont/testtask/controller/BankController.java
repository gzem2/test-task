package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.BankService;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BankController {
    @Autowired
    BankService bankService;

    @GetMapping("/banks")
    public ModelAndView showAllBanks() {
        ModelAndView modelAndView = new ModelAndView("bank/bank-list");
        modelAndView.addObject("listOfBanks", bankService.getAllBanks());
        return modelAndView;
    }

    @GetMapping("/bank/{id}")
    public String showBank(@PathVariable UUID id) {
        return "bank-details";
    }

    @GetMapping("/bank/new")
    public String showNewBankForm(Bank bank) {
        return "bank/bank-form";
    }

    @GetMapping("/bank/edit/{id}")
    public ModelAndView showEditBankForm(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("bank/bank-form");
        Bank bankForEdit = bankService.getBank(id);
        modelAndView.addObject("bank", bankForEdit);
        return modelAndView;
    }

    @PostMapping("/bank/new")
    public ModelAndView saveBank(@RequestParam Map<String, String> postBody) {
        String postId = postBody.get("id");
        if(postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);
            Bank editBank = bankService.getBank(id);
            if (editBank != null) {
                editBank.setBankName(postBody.get("bankName"));
                bankService.saveBank(editBank);
            }
        } else {
            Bank newBank = new Bank(postBody.get("bankName"), new ArrayList<Customer>(), new ArrayList<Credit>());
            bankService.saveBank(newBank);
        }
        return new ModelAndView("redirect:/banks");
    }

    @GetMapping("/bank/delete/{id}")
    public ModelAndView deleteBank(@PathVariable UUID id) {
        bankService.deleteBank(id);
        return new ModelAndView("redirect:/banks");
    }
}