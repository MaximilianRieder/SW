package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.service.CreditServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import de.othr.sw.quickstart.service.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String goStartPage(
            Model model
    ) {
//        //testcode
//        long l = 5;
//        transactionService.transfer("DE26000000000000000004", "DE26000000000000000002", l);
//        List<Transaction> list = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), 5);
//        for (Transaction t: list
//             ) {
//            model.addAttribute("transfereins", Long.toString(t.getAmount()));
//        }
//        return "startPage";

        //testcode 2
        long l = 500;
        creditService.requestCredit("DE26000000000000000004", l);
        List<Transaction> list = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), 5);
        Optional<Account> testa = accountRepository.findByIban("DE26000000000000000004");
        for (Transaction t: list
             ) {
            model.addAttribute("transfereins", Long.toString(t.getReceiver().getCredit().getAmount()));
        }
        return "startPage";
    }
}
