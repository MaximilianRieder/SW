package de.othr.sw.quickstart.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private boolean m26credit;
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account receiver;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isM26credit() {
        return m26credit;
    }

    public void setM26credit(boolean m26credit) {
        this.m26credit = m26credit;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }
}
