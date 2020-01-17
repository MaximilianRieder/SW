package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Account;

public interface AccountServiceIF {
    public Account createAccount(Account account, String username);
    public Account getAccountByIban(String iban);
    public boolean verifyAccount(String iban, String customerKey);
}
