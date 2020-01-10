package de.othr.sw.quickstart.service;

public interface TransferHandlerIF {
    public boolean sendMoney(String receiverIban, Long amount);
}
