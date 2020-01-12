package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.entity.Transaction;

import java.util.List;

public interface TransactionServiceIF {
    public boolean transfer(String senderIban, String receiverIban, Long amount);
    public List<Transaction> getLastTransactions(Customer customer);
}
