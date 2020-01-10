package de.othr.sw.quickstart.service;

public class TransferHandlerCustomer implements TransferHandlerIF {
    @Override
    public boolean sendMoney(String receiverIban, Long amount) {
        return true;
    }
}
