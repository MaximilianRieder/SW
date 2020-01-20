package de.othr.sw.quickstart.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String iban;
    private long balance;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<Credit> credits;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Customer accountHolder;

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    public long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }
}
