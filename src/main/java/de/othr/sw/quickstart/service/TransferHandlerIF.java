package de.othr.sw.quickstart.service;

public interface TransferHandlerIF {
    public boolean transferMoney(String receiverIban, String senderIban, Long amount);
}
