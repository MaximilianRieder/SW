package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.helpclassAndConfig.YAMLConfig;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.CreditRepository;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Scope("singleton")
public class TransferHandlerCredit implements TransferHandlerIF {
    @Autowired
    YAMLConfig yamlConfig;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditRepository creditRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

        //Update Credit @ Receiver
        ///calculate final credit amount (with interest)
        long amountCred = amount + Math.round(((double) amount * ((double)yamlConfig.getStandardInterestRate() / 1000)));
        //at least one cent income
        if (amountCred == amount)
            amountCred++;
        ///calculate repayment rate (at least 1)
        long repaymentRate = Math.round((double) amountCred / yamlConfig.getStandardRepaymentTime());
        if (repaymentRate == 0)
            repaymentRate++;

        //check if enough money
        if (senderAccount.getBalance() < amount) {
            //create failed transaction
            date = java.util.Calendar.getInstance().getTime();
            Transaction transaction = new Transaction();
            transaction.setDate(date);
            transaction.setAmount(amount);

            //set credit flag in transaction
            transaction.setM26credit(true);

            transaction.setSender(senderAccount);
            transaction.setReceiver(receiverAccount);
            transaction.setStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            Optional<Transaction> transO = Optional.of(transaction);
            return transO;
        } else {
            //transaction worked
            //set new balance
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            //make credit and safe
            Credit credit = new Credit();
            credit.setRepaymentRate(repaymentRate);
            credit.setInterestRate(yamlConfig.getStandardInterestRate());
            credit.setAmount(amount);
            credit.setRemainingAmountBack(amountCred);
            credit.setActiveCredit(true);
            creditRepository.save(credit);

            //set credit
            receiverAccount.addCredit(credit);

            //////////////////////////////////////////
            //update accounts
            //accountRepository.save(senderAccount);
            //accountRepository.save(receiverAccount);

            //create working transaction
            date = java.util.Calendar.getInstance().getTime();
            Transaction transaction = new Transaction();
            transaction.setDate(date);
            //set the actual credit amount with interestRate
            transaction.setAmount(amount);
            //set credit flag in transaction
            transaction.setM26credit(true);
            transaction.setSender(senderAccount);
            transaction.setReceiver(receiverAccount);
            transaction.setStatus(TransactionStatus.SUCCESS);
            transaction = transactionRepository.save(transaction);
            Optional<Transaction> transO = Optional.of(transaction);
            return transO;
        }
    }
}
