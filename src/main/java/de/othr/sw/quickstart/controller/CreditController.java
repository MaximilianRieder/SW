package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CreditServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import de.othr.sw.quickstart.service.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Scope("session")
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditServiceIF creditService;
    @Autowired
    AccountServiceIF accountService;
    @Autowired
    CustomerServiceIF customerService;

    @RequestMapping("/request")
    public String requestCredit(
            Model model,
            @ModelAttribute("amount") long amount,
            @ModelAttribute("senderIban") String iban
    ) {
        if((accountService.getAccountByIban(iban).isEmpty()) || (amount <= 0)) {
            model.addAttribute("accounts", accountService.getAccountsByCustomer(customerService.getLoggedInCustomer()));
            return "credit";
        } else {
            creditService.requestCredit(iban, amount);
            return "credit";
        }
    }
}

