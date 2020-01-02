package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
