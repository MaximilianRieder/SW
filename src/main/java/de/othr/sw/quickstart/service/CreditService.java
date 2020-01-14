package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;

import de.othr.sw.quickstart.helpclass.M26Config;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CreditService implements CreditServiceIF{

    @Autowired
    @Qualifier("TransferHandlerCredit")
    private TransferHandlerIF transferHandlerCredit;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public boolean requestCredit(String receiverIban, long amount) {
        //check if account exists and if there is already a credit on the account (only one credit per account allowed)
        if (accountRepository.findByIban(receiverIban).isEmpty()) {
            return false;
        }
        if (accountRepository.findByIban(receiverIban).get().getCredit().isActiveCredit()) {
            return false;
        }
        //get accounts from bank
        List<Account> bAccounts = accountRepository.findByAccountHolder_Username(M26Config.bankName);
        //for each mit abbruch falls funktioniert hat
        for (Account a: bAccounts
             ) {
            if(transferHandlerCredit.transferMoney(a.getIban(), receiverIban, amount)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
