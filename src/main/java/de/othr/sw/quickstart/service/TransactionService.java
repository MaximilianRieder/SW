package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.dtos.TransactionMessage;
import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.AccountRepository;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionService implements TransactionServiceIF {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    @Qualifier("TransferHandlerCustomer")
    private TransferHandlerIF transferHandlerCustomer;

    @Transactional
    @Override
    public Optional<Transaction> transfer(String senderIban, String receiverIban, long amount) {
        Optional<Transaction> transO = transferHandlerCustomer.transferMoney(senderIban, receiverIban, amount);
        return transO;
    }

    @Override
    public List<Transaction> getLastTransactions(Customer customer, int number) {
        List<Account> accounts = customer.getAccounts();
        //reduce the number in relation to number of accounts, to avoid too much load
        number = (number / accounts.size());
        List<Transaction> transactions = new LinkedList<>();
        for (Account a: accounts
             ) {
            Pageable pageable = PageRequest.of(0, number, Sort.by("date").descending());
            Page<Transaction> page = transactionRepository.findByReceiver_IdOrSender_Id(a.getId(), a.getId(), pageable);
            transactions.addAll(page.getContent());
        }
        return transactions;
    }
}
