package de.othr.sw.quickstart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tID;
    private long amount;
    private Date date;
    private boolean m26credit;
    private Account sender;
    private Account receiver;
}
