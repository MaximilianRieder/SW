package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;

public interface AccountServiceIF {
    public void createAccount(Account account, String username);
    public String createNewIban(Account account);
    public Account getAccountByIban(String iban);
}
