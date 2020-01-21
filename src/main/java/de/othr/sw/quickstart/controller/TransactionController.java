package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.helpclass.YAMLConfig;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    AccountServiceIF accountService;
    @Autowired
    YAMLConfig yamlConfig;

    @RequestMapping("/transafer")
    public String doTransaction(
            Model model,
            @ModelAttribute("senderIban") String senderIban,
            @ModelAttribute("receiverIban") String receiverIban,
            @ModelAttribute("amount") long amount
    ) {
        Optional<Transaction> transO;
        //check if sender iban is a correct iban
        if(accountService.getAccountByIban(senderIban).isPresent()) {
            //check if sender(logged in) is owner of account
            if(customerService.getLoggedInCustomer().getUsername().equals(accountService.getAccountByIban(senderIban).get().getAccountHolder().getUsername())){
                //make transaction and check if worked
                transO = transactionService.transfer(senderIban, receiverIban, amount);
                if(transO.isEmpty()) {
                    model.addAttribute("transactionStatus","false receiver iban");
                } else {
                    if(transO.get().getStatus() == TransactionStatus.SUCCESS) {
                        model.addAttribute("transactionStatus","success");
                    } else {
                        model.addAttribute("transactionStatus","not enough money in account");
                    }
                }
            } else {
                model.addAttribute("transactionStatus","choose one of your own accounts");
            }
        } else {
            model.addAttribute("transactionStatus","choose a correct iban for your transaction");
        }
        model.addAttribute("accounts", accountService.getAccountsByCustomer(customerService.getLoggedInCustomer()));
        return "banking";
    }

    @RequestMapping("history")
    public String showTransactionsMore(
            Model model
    ) {
        if(customerService.getLoggedInCustomer().getAccounts().isEmpty())
            return "accountManagement";

        //returns history with number (rounded -> after dividation through account number)
        List<Transaction> transactions = transactionService.getLastTransactions(customerService.getLoggedInCustomer(), yamlConfig.getHistoryNumber());
        model.addAttribute("transactions", transactions);

        return "accountManagement";
    }
}
