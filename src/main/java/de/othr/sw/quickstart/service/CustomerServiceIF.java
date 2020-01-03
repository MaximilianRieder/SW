package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Customer;

public interface CustomerServiceIF {
    public void createCustomer(Customer customer);
    public boolean isUsernameUsed(String username);
    public void createAddress(Address address);
}
