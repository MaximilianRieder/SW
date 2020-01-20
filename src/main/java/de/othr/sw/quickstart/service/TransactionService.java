package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.remoteRequest.RemoteSchufaHandlerIF;
import de.othr.sw.quickstart.repository.AccountRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Scope("singleton")
public class TransactionService implements TransactionServiceIF {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    @Qualifier("TransferHandlerCustomer")
    private TransferHandlerIF transferHandlerCustomer;
    @Autowired
    RemoteSchufaHandlerIF remoteSchufaHandler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Transaction> transfer(String senderIban, String receiverIban, long amount) {
        Optional<Transaction> transO = transferHandlerCustomer.transferMoney(senderIban, receiverIban, amount);

        //notify schufa
        if((accountRepository.findByIban(senderIban).isEmpty()) || (accountRepository.findByIban(receiverIban).isEmpty()))
            return transO;
        String receiverName = accountRepository.findByIban(receiverIban).get().getAccountHolder().getFirstName() + "" + accountRepository.findByIban(receiverIban).get().getAccountHolder().getLastName();
        String senderName = accountRepository.findByIban(senderIban).get().getAccountHolder().getFirstName() + "" + accountRepository.findByIban(senderIban).get().getAccountHolder().getLastName();
        //check if there was a transaction
        if (transO.isEmpty())
            return transO;
        //noitfy with responding status
        if (transO.get().getStatus() == TransactionStatus.SUCCESS) {
            remoteSchufaHandler.updateUser(receiverName, Art.ZAHLUNGSEINGANG, (int)amount);
            remoteSchufaHandler.updateUser(senderName, Art.ZAHLUNGERFOLGREICH, (int) amount);
            return transO;
        } else if (transO.get().getStatus() == TransactionStatus.FAILURE) {
            remoteSchufaHandler.updateUser(senderName, Art.ZAHLUNGABGELEHNT, (int) amount);
            return transO;
        } else {
            return transO;
        }
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
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                if (t1.getDate() == null || t2.getDate() == null)
                    return 0;
                return t1.getDate().compareTo(t2.getDate());
            }
        });
        return transactions;
    }
}
