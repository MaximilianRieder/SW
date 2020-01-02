package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("labresources")
public class CustomerService implements CustomerServiceIF, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Customer customer = customerRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> {
                            throw new UsernameNotFoundException("Customer with Nr. " + id + " doesnt exist");
                        }
                );
        return customer;
    }
}
