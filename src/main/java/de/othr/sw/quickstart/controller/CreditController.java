package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.AccountRepository;
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
    TransactionServiceIF transactionService;
    @Autowired
    CustomerServiceIF customerService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditServiceIF creditService;
    @RequestMapping("/request")
    public String requestCredit(
            Model model,
            @ModelAttribute("amount") long amount,
            @ModelAttribute("account") String iban
    ) {
        creditService.requestCredit(iban, amount);
//        List<Transaction> list = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), 5);
//        Optional<Account> testa = accountRepository.findByIban("DE26000000000000000004");
//        for (Transaction t: list
//        ) {
//            for (Credit c:t.getReceiver().getCredits()
//            ) {
//                model.addAttribute("transfereins", Long.toString(c.getAmount()));
//            }
//        }
        return "startPage";
    }
}

