package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements AccountServiceIF{
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public void createAccount(Account account, String username) {
        Customer accountHolder = customerRepository.findByUsername(username).get();
        account = accountRepository.save(account);
        String iban = createNewIban(account);
        System.out.println(iban);
        account.setIban(iban);
//        account.setCreditAmount(0);
//        account.setInterestRate(0);
//        account.setCredit(null);
        accountHolder.addAccount(account);
        accountHolder = customerRepository.save(accountHolder);
        account = accountRepository.save(account);
        return;
    }

    //create IBAN for new Account (Is no real IBAN, its just for demo purposes(no real BLZ etc.))
    public String createNewIban(Account account) {
        //standard IBAN header for M26
        int ibanLength = 18;
        String pre = "DE26";
        String iban;
        //create rest of iban
        String uniqueID = Long.toString(account.getId());
        //use accountID for creation
        uniqueID = fillWithZero(uniqueID, ibanLength);
        iban = pre + uniqueID;
        // check if somehow already used or too long -> try random one until found
        while (true) {
            if ((accountRepository.findByIban(iban).isEmpty()) && (iban.length() == (ibanLength + pre.length()))) {
                return iban;
            } else {
                long randomIbanDigits = (long) ((Math.random() * Math.pow(10, (double) (ibanLength - 1)) * 9) + Math.pow(10, (double) (ibanLength - 1)));
                iban = pre + fillWithZero(Long.toString(randomIbanDigits), ibanLength);
            }
        }
    }

    private String fillWithZero(String uniqueID, Integer until) {
        if(uniqueID.length() < until){
            int a = until - uniqueID.length();
            for (int i = 0; i < a; i++) {
                uniqueID = "0" + uniqueID;
            }
        }
        return uniqueID;
    }

    //löschen
    @Override
    public Account getAccountByIban(String iban) {
        return accountRepository.findByIban(iban).get();
    }
}
