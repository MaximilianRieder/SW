package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Address;
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
        accountRepository.save(account);
        accountHolder.addAccount(account);
        customerRepository.save(accountHolder);
        accountRepository.save(account);
        return;
    }

    //create IBAN for new Account (Is no real IBAN, its just for demo purposes(no real BLZ etc.))
    @Override
    public String createNewIban(Account account) {
        //standard IBAN header for M26
        Integer ibanLength = 18;
        String pre = "DE26";
        String iban;
        //create rest of iban
        String uniqueID = Long.toString(account.getaID());
        //use accountID for creation
        if(uniqueID.length() < ibanLength){
            int a = ibanLength - uniqueID.length();
            for (int i = 0; i < a; i++) {
                uniqueID = "0" + uniqueID;
            }
        }
        iban = pre + uniqueID;
        // check if somehow already used or too long -> try random one until found
        while (true) {
            if ((accountRepository.findByIban(iban).isEmpty()) && (iban.length() == (ibanLength + pre.length()))) {
                return iban;
            } else {
                int randomIbanDigits = (int) ((Math.random() * Math.pow(10, (double) (ibanLength - 1)) * 9) + Math.pow(10, (double) (ibanLength - 1)));
                iban = pre + randomIbanDigits;
            }
        }
    }

    @Override
    public Account getAccountByIban(String iban) {
        return accountRepository.findByIban(iban).get();
    }
}
