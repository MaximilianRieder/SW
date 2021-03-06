package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.CustomerRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Scope("singleton")
public class AccountService implements AccountServiceIF{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Account createAccount(Account account, long id) {
        Customer accountHolder = customerRepository.findById(id).get();
        account = accountRepository.save(account);
        String iban = createNewIban(account);
        account.setCredits(null);
        account.setIban(iban);
        accountHolder.addAccount(account);
        return account;
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

    //besser prüfen Optional auch drunter
    @Override
    public Optional<Account> getAccountByIban(String iban) {
        return accountRepository.findByIban(iban);
    }

    @Override
    public List<Account> getAccountsByCustomer(Customer customer) {
        return accountRepository.findAccountsByAccountHolder_Id(customer.getId());
    }

    @Override
    public boolean verifyAccount(String iban, String customerKey) {
        Optional<Account> accountO = accountRepository.findByIban(iban);
        if(accountO.isEmpty()) {
            return false;
        } else {
            Account account = accountO.get();
            if (account.getAccountHolder().getCustomerKey().toString().equals(customerKey)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
