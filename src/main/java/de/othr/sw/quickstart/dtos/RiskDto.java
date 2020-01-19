package de.othr.sw.quickstart.dtos;

import java.io.Serializable;

public class RiskDto implements Serializable {
    int CustomerID;
    String name;
    String geburtsdatum;
    int betrag;

    public RiskDto() {
    }

    public RiskDto(int customerID, String name, String geburtsdatum, int betrag) {
        CustomerID = customerID;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.betrag = betrag;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }
}
