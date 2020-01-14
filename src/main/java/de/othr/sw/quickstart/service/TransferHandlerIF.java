package de.othr.sw.quickstart.service;

public interface TransferHandlerIF {
    public boolean transferMoney(String senderIban, String receiverIban, long amount);
}
