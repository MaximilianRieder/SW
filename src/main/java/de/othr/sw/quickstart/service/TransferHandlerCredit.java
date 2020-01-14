package de.othr.sw.quickstart.service;

public class TransferHandlerCredit implements TransferHandlerIF {
    @Override
    public boolean transferMoney(String senderIban, String receiverIban, long amount) {
        return true;
    }
}
