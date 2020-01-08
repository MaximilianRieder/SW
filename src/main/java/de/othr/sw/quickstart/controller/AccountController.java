package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.service.AccountService;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Scope("session")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountServiceIF accountService;
    @RequestMapping("/create")
    public String goStartPage(
            Model model
    ) {
        Account account = new Account();
        //account.setIban(accountService.createNewIban(account));
        account.setIban("testIban");
        //5000 euro start balance (500000 cent)
        account.setBalance(500000);
        account.setCreditAmount(0);
        account.setInterestRate(0);
        account.setAccountHolder(null);
        accountService.createAccount(account, customerService.getLoggedInCustomer().getUsername());
        model.addAttribute("accountBalance", customerService.getLoggedInCustomer().getAccounts().get(0).getBalance());
        return "startPage";
    }
}