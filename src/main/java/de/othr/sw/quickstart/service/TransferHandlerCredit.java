package de.othr.sw.quickstart.service;

public class TransferHandlerCredit implements TransferHandlerIF {
    @Override
    public boolean sendMoney(String receiverIban, Long amount) {
        return true;
    }
}
