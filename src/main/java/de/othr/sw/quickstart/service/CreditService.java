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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService implements CreditServiceIF{

    @Autowired
    @Qualifier("TransferHandlerCredit")
    private TransferHandlerIF transferHandlerCredit;

    @Autowired
    @Qualifier("TransferHandlerCustomer")
    private TransferHandlerIF transferHandlerCustomer;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditRepository creditRepository;


    @Transactional
    @Override
    public boolean requestCredit(String receiverIban, long amount) {
        if (accountRepository.findByIban(receiverIban).isEmpty()) {
            return false;
        }
        //get accounts from bank
        List<Account> bAccounts = accountRepository.findByAccountHolder_Username(M26Config.bankName);
        //for each mit abbruch falls funktioniert hat
        for (Account a: bAccounts
             ) {
            Optional<Transaction> transactionO = transferHandlerCredit.transferMoney(a.getIban(), receiverIban, amount);
            if(transactionO.isPresent()) {
                if (transactionO.get().getStatus() == TransactionStatus.SUCCESS){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Scheduled(cron = M26Config.creditRepayRate)
    public void scheduledRepay() {
        //get all acounts
        Iterable<Account> accountsIterable = accountRepository.findAll();
        for (Account a : accountsIterable
             ) {
            //skip bank account
            if (!(a.getAccountHolder().getUsername().equals(M26Config.bankName))){
                repayCreditRate(a);
            } else {
            }
        }
    }

    public void repayCreditRate(Account a) {
        //send money to first account of bank m26
        String bankIban = accountRepository.findByAccountHolder_Username(M26Config.bankName).get(0).getIban();
        //if account is account from bank return
        //get credits
        List<Credit> credits = a.getCredits();
        for (Credit c: credits
             ) {
            if(c.isActiveCredit()) {
                long repaymentRate = c.getRepaymentRate();
                //check if repayment rate is higher than remaining amount
                if((c.getRemainingAmountBack() - repaymentRate) <= 0) {
                    repaymentRate = c.getRemainingAmountBack();
                }
                Optional<Transaction> transO = transferHandlerCustomer.transferMoney(a.getIban(), bankIban, repaymentRate);
                //throw exception
                if(transO.isEmpty())
                    return;
                Transaction transaction = transO.get();
                if(transaction.getStatus() == TransactionStatus.SUCCESS) {
                    //repayment worked
                    if((c.getRemainingAmountBack() - repaymentRate) > 0) {
                        c.setRemainingAmountBack(c.getRemainingAmountBack() - repaymentRate);
                    } else {
                        c.setRemainingAmountBack(0);
                        c.setActiveCredit(false);
                    }
                    creditRepository.save(c);
                } else {
                    //repayment didnt work
                    /// punitive interest (customer cant pay -> customer has to pay remaining credit * standard interest rate)
                    c.setRemainingAmountBack(c.getRemainingAmountBack() + Math.round(((double)c.getRemainingAmountBack() * ((double)M26Config.standardInterestRate / 1000))));
                    creditRepository.save(c);
                }
            }
        }
    }
}
