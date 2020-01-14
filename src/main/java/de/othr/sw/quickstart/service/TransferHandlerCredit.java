package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.helpclass.M26Config;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.CreditRepository;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class TransferHandlerCredit implements TransferHandlerIF {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditRepository creditRepository;
    Date date;

    @Override
    public boolean transferMoney(String senderIban, String receiverIban, long amount) {
        //check if ibans correct
        Optional<Account> receiverAccountO = accountRepository.findByIban(receiverIban);
        Optional<Account> senderAccountO = accountRepository.findByIban(senderIban);
        if((receiverAccountO.isEmpty()) || (senderAccountO.isEmpty())) {
            return false;
        }
        Account receiverAccount = receiverAccountO.get();
        Account senderAccount = senderAccountO.get();

        //Update Credit @ Receiver
        ///calculate final credit amount (with interest)
        long amountCred = amount + Math.round(((double) amount * ((double)M26Config.standardInterestRate / 1000)));
        ///calculate repayment rate
        long repaymentRate = Math.round((double) amountCred / M26Config.standardRepaymentTime);

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
            transactionRepository.save(transaction);
            return false;
        } else {
            //transaction worked
            //set new balance
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            //make credit and safe
            Credit credit = new Credit();
            credit.setRepaymentRate(repaymentRate);
            credit.setInterestRate(M26Config.standardInterestRate);
            credit.setAmount(amount);
            credit.setRemainingAmountBack(amountCred);
            credit.setActiveCredit(true);
            creditRepository.save(credit);

            //set credit
            receiverAccount.addCredit(credit);

            //update accounts
            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);

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
            transactionRepository.save(transaction);
            return true;
        }
    }
}
