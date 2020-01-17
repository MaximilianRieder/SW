package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Transaction;

import java.util.Optional;

public interface TransferHandlerIF {
    public Optional<Transaction> transferMoney(String senderIban, String receiverIban, long amount);
}
