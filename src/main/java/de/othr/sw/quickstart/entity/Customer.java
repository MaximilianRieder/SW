package de.othr.sw.quickstart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.LinkedList;

//@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cID;
    private String firstName;
    private String lastName;
    private String password;
    private Adress mainResidence;
    private LinkedList<Account> accounts;

    public long getcID() {
        return cID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adress getMainResidence() {
        return mainResidence;
    }

    public void setMainResidence(Adress mainResidence) {
        this.mainResidence = mainResidence;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(LinkedList<Account> accounts) {
        this.accounts = accounts;
    }
}
