package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.dtos.Risikostufe;

public interface CreditServiceIF {
    public boolean requestCredit(String receiverIban, long amount);
    public Risikostufe getRisikoStufe(String iban, long amount);
}
