package de.othr.sw.quickstart.entity;

import javax.persistence.*;

@Entity
public class CustomerRight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int customerRightID;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Right right;

    public int getCustomerRightID() {
        return customerRightID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }
}