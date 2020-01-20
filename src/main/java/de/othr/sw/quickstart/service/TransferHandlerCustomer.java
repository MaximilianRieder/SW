package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Transactional
public class TransferHandlerCustomer implements TransferHandlerIF {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Transaction> transferMoney(String senderIban, String receiverIban, long amount) {
        Date date;
        //check if ibans correct
        Optional<Account> receiverAccountO = accountRepository.findByIban(receiverIban);
        Optional<Account> senderAccountO = accountRepository.findByIban(senderIban);
        if((receiverAccountO.isEmpty()) || (senderAccountO.isEmpty())) {
            return Optional.empty();
        }
        Account receiverAccount = receiverAccountO.get();
        Account senderAccount = senderAccountO.get();
        //check if enough money
        if (senderAccount.getBalance() < amount) {
            //create failed transaction
            date = java.util.Calendar.getInstance().getTime();
            Transaction transaction = new Transaction();
            transaction.setDate(date);
            transaction.setAmount(amount);
            transaction.setM26credit(false);
            transaction.setSender(senderAccount);
            transaction.setReceiver(receiverAccount);
            transaction.setStatus(TransactionStatus.FAILURE);

            transaction = transactionRepository.save(transaction);
            Optional<Transaction> transO = Optional.of(transaction);
            return transO;
        } else {
            //transaction worked
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);
            //create working transaction
            date = java.util.Calendar.getInstance().getTime();
            Transaction transaction = new Transaction();
            transaction.setDate(date);
            transaction.setAmount(amount);
            transaction.setM26credit(false);
            transaction.setSender(senderAccount);
            transaction.setReceiver(receiverAccount);
            transaction.setStatus(TransactionStatus.SUCCESS);

            transaction = transactionRepository.save(transaction);
            Optional<Transaction> transO = Optional.of(transaction);
            return transO;
        }
    }
}
