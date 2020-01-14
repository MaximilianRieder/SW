package de.othr.sw.quickstart.service;

public interface CreditServiceIF {
    public boolean requestCredit(String receiverIban, long amount);
}
