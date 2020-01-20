package de.othr.sw.quickstart.helpclass;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.CustomerRepository;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BankRunner implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountServiceIF accountService;
    @Autowired
    YAMLConfig yamlConfig;

    //initializes Bank data
    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.findByUsername(yamlConfig.getBankName()).isPresent()) {
            return;
        }
        Address address = new Address();
        address.setStreet("Maidenbergstraße");
        address.setNumber("1");
        address.setZip("93059");
        address.setCity("Regensburg");
        address.setCountry("Germany");
        Customer customer = new Customer();
        customer.setFirstName(yamlConfig.getBankName());
        customer.setLastName(yamlConfig.getBankName());
        customer.setPassword(yamlConfig.getBankName());
        customer.setUsername(yamlConfig.getBankName());
        //gründung
        String birthday = "01-01-2000";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(birthday);
        customer.setBirthday(date);
        customer.setAccounts(null);
        customer.setMainResidence(address);
        customer = customerService.createCustomer(customer);
        Account account = new Account();
        //5000000 euro start balance (500000000 cent)
        account.setBalance(500000000);
        accountService.createAccount(account, customer.getId());
    }
}
