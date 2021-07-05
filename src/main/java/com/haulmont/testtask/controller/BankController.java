package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CustomerService;
import java.util.HashSet;
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
public class BankController {
    @Autowired
    BankService bankService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/banks")
    public ModelAndView showAllBanks() {
        ModelAndView modelAndView = new ModelAndView("bank/bank-list");
        modelAndView.addObject("listOfBanks", bankService.getAllBanks());
        return modelAndView;
    }

    @GetMapping("/bank/{id}")
    public ModelAndView showBank(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("bank/bank-details");
        modelAndView.addObject("bank", bankService.getBank(id).orElseGet(() -> new Bank()));
        modelAndView.addObject("creditOffers", bankService.getCreditOffersForBankId(id));
        return modelAndView;
    }

    @GetMapping("/bank/new")
    public String showNewBankForm(Bank bank) {
        return "bank/bank-form";
    }

    @GetMapping("/bank/customer/new")
    public ModelAndView showNewBankCustomerForm(@RequestParam UUID bank, @RequestParam Optional<UUID> customer) {
        ModelAndView modelAndView = new ModelAndView("bank/bank-customer-form");
        modelAndView.addObject("bank", bankService.getBank(bank).orElseGet(() -> new Bank()));

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customerService.getCustomer(customer.get()));
        } else {
            modelAndView.addObject("customer", new Customer());
        }

        modelAndView.addObject("searchCustomer", new Customer());
        return modelAndView;
    }

    @PostMapping("/bank/search/customer")
    public ModelAndView bankSearchCustomer(Customer searchCustomer, Customer customer, @RequestParam UUID bank) {
        ModelAndView modelAndView = new ModelAndView("bank/bank-customer-form");
        modelAndView.addObject("bank", bankService.getBank(bank).orElseGet(() -> new Bank()));

        List<Customer> foundCustomers = customerService.searchCustomer(searchCustomer);
        modelAndView.addObject("foundCustomers", foundCustomers);
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("searchCustomer", searchCustomer);

        return modelAndView;
    }

    @PostMapping("/bank/new/customer")
    public ModelAndView addCustomerToBank(Customer customer, @RequestParam UUID bank) {
        Optional<Bank> optBank = bankService.getBank(bank);

        if (optBank.isPresent()) {
            customerService.saveCustomer(customer);
            bankService.addCustomerToBank(optBank.get(), customer);
        }

        return new ModelAndView("redirect:/bank/" + bank);
    }

    @GetMapping("/bank/remove/customer")
    public ModelAndView removeCustomerFromBank(@RequestParam UUID bank, @RequestParam UUID customer) {
        Optional<Customer> optCustomer = customerService.getCustomer(customer);

        if (optCustomer.isPresent()) {
            Customer customerForRemoval = optCustomer.get();

            Optional<Bank> optBank = bankService.getBank(bank);
            if (optBank.isPresent()) {
                bankService.removeCustomerFromBank(optBank.get(), customerForRemoval);
            }
        }

        return new ModelAndView("redirect:/bank/" + bank);
    }

    @GetMapping("/bank/edit/{id}")
    public ModelAndView showEditBankForm(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("bank/bank-form");
        Bank bankForEdit = bankService.getBank(id).orElseGet(() -> new Bank());
        modelAndView.addObject("bank", bankForEdit);
        return modelAndView;
    }

    @PostMapping("/bank/new")
    public ModelAndView saveBank(@RequestParam Map<String, String> postBody) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<Bank> optEditBank = bankService.getBank(id);

            if (optEditBank.isPresent()) {
                Bank editBank = optEditBank.get();
                editBank.setBankName(postBody.get("bankName"));
                bankService.saveBank(editBank);
            }
        } else {
            Bank newBank = new Bank(postBody.get("bankName"), new HashSet<Customer>(), new HashSet<Credit>());
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