package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Scope("session")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountServiceIF accountService;
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String goStartPage(
            Model model
    ) {
        Account account = new Account();
        //5000 euro start balance (500000 cent)
        account.setBalance(500000);
        account.setAccountHolder(null);

        if (customerService.getLoggedInCustomer().getAccounts().size() > 10) {
            model.addAttribute("accountCreationMessage","Creation limit reached, contact us if you want more account space.");
            return "accountManagement";
        }
        account = accountService.createAccount(account, customerService.getLoggedInCustomer().getId());
        model.addAttribute("accountCreationMessage","New Account: " + account.getIban());
        return "accountManagement";
    }
}