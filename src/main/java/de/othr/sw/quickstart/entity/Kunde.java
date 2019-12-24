package de.othr.sw.quickstart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kundenNr;
    private String vorname;
    private String nachname;


    public long getKundenNr() {
        return kundenNr;
    }

    public void setKundenNr(long kundenNr) {
        this.kundenNr = kundenNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
}
