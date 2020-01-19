package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountServiceIF {
    public List<Account> getAccountsByCustomer(Customer customer);
    public Account createAccount(Account account, long id);
    public Optional<Account> getAccountByIban(String iban);
    public boolean verifyAccount(String iban, String customerKey);
}
