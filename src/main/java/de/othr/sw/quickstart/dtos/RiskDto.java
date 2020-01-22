package de.othr.sw.quickstart.dtos;

import java.io.Serializable;

public class RiskDto implements Serializable {
    String apiKey;
    String name;
    String geburtsdatum;
    int betrag;

    public RiskDto() {
    }

    public RiskDto(String apiKey, String name, String geburtsdatum, int betrag) {
        this.apiKey = apiKey;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.betrag = betrag;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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
