package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Customer;

import java.util.Optional;

public interface CustomerServiceIF {
    public Customer createCustomer(Customer customer);
    public boolean isUsernameUsed(String username);
    //public void createAddress(Address address);
    public Customer getLoggedInCustomer();
}
