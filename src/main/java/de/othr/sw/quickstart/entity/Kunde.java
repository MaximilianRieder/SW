package de.othr.sw.quickstart.entity;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class Kunde {//implements UserDetails {

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

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorites = new HashSet<>();
        for(KundenRecht kundenRecht : this.kundenRechte) {
            authorites.add(new Authority(kundenRecht.getRecht().getRechteName()));
        }
        return authorites;
    }*/
}
