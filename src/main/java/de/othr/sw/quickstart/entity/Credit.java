package de.othr.sw.quickstart.entity;

import javax.persistence.*;

@Embeddable
public class Credit {
    private long amount;
    private long interestRate;
    private boolean activeCredit;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(long interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isActiveCredit() {
        return activeCredit;
    }

    public void setActiveCredit(boolean activeCredit) {
        this.activeCredit = activeCredit;
    }
}