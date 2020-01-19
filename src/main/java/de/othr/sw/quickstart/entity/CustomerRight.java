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
    private Right2 right2;

    public int getCustomerRightID() {
        return customerRightID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Right2 getRight2() {
        return right2;
    }

    public void setRight2(Right2 right2) {
        this.right2 = right2;
    }
}