package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Qualifier("customer")
public class CustomerService implements CustomerServiceIF, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        customer.setCustomerKey(uuid);
        customerRepository.save(customer);
        return customer;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> {
                            throw new UsernameNotFoundException("Customer with Nr. " + username + " doesnt exist");
                        }
                );
        return customer;
    }

    @Override
    public boolean isUsernameUsed(String username) {
        if(customerRepository.findByUsername(username).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Customer getLoggedInCustomer() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            Optional<Customer> cust = customerRepository.findByUsername(username);
            Customer customer = cust.get();
            return customer;
        } else {
            username = principal.toString();
            Optional<Customer> cust = customerRepository.findByUsername(username);
            Customer customer = cust.get();
            return customer;
        }
    }
}
