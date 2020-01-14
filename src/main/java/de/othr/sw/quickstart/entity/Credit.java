package de.othr.sw.quickstart.entity;

import javax.persistence.*;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amount;
    //interest Rate in promille
    private long interestRate;
    //amount of money per month payed back
    private long repaymentRate;
    //to check if there is a credit
    private boolean activeCredit;
    private long remainingAmountBack;

    public long getRemainingAmountBack() {
        return remainingAmountBack;
    }

    public void setRemainingAmountBack(long remainingAmount) {
        this.remainingAmountBack = remainingAmount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getRepaymentRate() {
        return repaymentRate;
    }

    public void setRepaymentRate(long repaymentRate) {
        this.repaymentRate = repaymentRate;
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