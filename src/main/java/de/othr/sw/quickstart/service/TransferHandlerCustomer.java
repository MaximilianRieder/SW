package de.othr.sw.quickstart.service;

public class TransferHandlerCustomer implements TransferHandlerIF {
    @Override
    public boolean sendMoney(String receiverIban, Long amount) {
        //check if enough money
        //create entitys / change entitys
        //persist

        return true;
    }
}
