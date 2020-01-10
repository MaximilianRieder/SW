package de.othr.sw.quickstart.helpclass;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.CustomerRepository;
import de.othr.sw.quickstart.service.AccountService;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerService;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BankRunner implements CommandLineRunner {

    @Autowired
    private CustomerServiceIF customerService;

    @Autowired
    private AccountServiceIF accountService;

    //initializes Bank data
    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setStreet("Maidenbergstraße");
        address.setNumber("1");
        address.setZip("93059");
        address.setCity("Regensburg");
        address.setCountry("Germany");
        Customer customer = new Customer();
        customer.setFirstName("m26");
        customer.setLastName("m26");
        customer.setPassword("m26");
        customer.setUsername("m26");
        customer.setAccounts(null);
        customer.setMainResidence(address);
        customerService.createCustomer(customer);
        Account account = new Account();
        account.setIban("testIban");
        //5000000 euro start balance (500000000 cent)
        account.setBalance(500000000);
        account.setCreditAmount(0);
        account.setInterestRate(0);
        accountService.createAccount(account, "m26");
    }
}
