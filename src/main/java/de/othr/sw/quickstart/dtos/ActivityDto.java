package de.othr.sw.quickstart.dtos;

import java.io.Serializable;

public class ActivityDto implements Serializable {
    String name;
    Art art;
    int betrag;
    String nameKunde;
    String geburtsdatum;

    public ActivityDto() {
    }

    public ActivityDto(String name, Art art, int betrag, String nameKunde, String geburtsdatum) {
        this.name = name;
        this.art = art;
        this.betrag = betrag;
        this.nameKunde = nameKunde;
        this.geburtsdatum = geburtsdatum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Art getArt() {
        return art;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public String getNameKunde() {
        return nameKunde;
    }

    public void setNameKunde(String nameKunde) {
        this.nameKunde = nameKunde;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
