package de.othr.sw.quickstart.service;

public class TransferHandlerCredit implements TransferHandlerIF {
    @Override
    public boolean transferMoney(String receiverIban, String senderIban, Long amount) {
        return true;
    }
}
