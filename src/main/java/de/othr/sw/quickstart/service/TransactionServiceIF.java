package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Transaction;

public interface TransactionServiceIF {
    public boolean transfer(String senderIban, String receiverIban, Long amount);
    public void createTransaction(Transaction transaction);
}
