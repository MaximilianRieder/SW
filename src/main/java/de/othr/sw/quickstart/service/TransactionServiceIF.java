package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionServiceIF {
    public Optional<Transaction> transfer(String senderIban, String receiverIban, long amount);
    public List<Transaction> getLastTransactions(Customer customer, int number);
}
