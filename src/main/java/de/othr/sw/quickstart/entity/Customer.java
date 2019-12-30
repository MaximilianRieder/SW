package de.othr.sw.quickstart.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cID;
    private String firstName;
    private String lastName;
    private String password;
    @ManyToOne
    private Address mainResidence;
    //@ElementCollection
    @OneToMany(mappedBy = "accountHolder")
    private List<Account> accounts;

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

    public Address getMainResidence() {
        return mainResidence;
    }

    public void setMainResidence(Address mainResidence) {
        this.mainResidence = mainResidence;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
