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
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServiceIF transactionService;
    @Autowired
    CustomerServiceIF customerService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditServiceIF creditService;

    @RequestMapping("/transafer")
    public String doTransaction(
            Model model,
            @ModelAttribute("senderIban") String senderIban,
            @ModelAttribute("receiverIban") String receiverIban,
            @ModelAttribute("amount") long amount
    ) {
        transactionService.transfer(senderIban, receiverIban, amount);
//        List<Transaction> list = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), 5);
//        for (Transaction t: list
//             ) {
//            model.addAttribute("transfereins", Long.toString(t.getAmount()));
//        }
        return "banking";
    }

    @RequestMapping("history")
    public String showTransactions(
            Model model
    ) {
        List<Transaction> transactions = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), 20);
        model.addAttribute("transactions", transactions);
        return "accountManagement";
    }
}
