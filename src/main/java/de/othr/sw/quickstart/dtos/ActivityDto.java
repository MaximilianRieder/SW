package de.othr.sw.quickstart.dtos;

import java.io.Serializable;

public class ActivityDto implements Serializable {
    String Name;
    Art art;
    int betrag;

    public ActivityDto() {
    }

    public ActivityDto(String name, Art art, int betrag) {
        Name = name;
        this.art = art;
        this.betrag = betrag;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
