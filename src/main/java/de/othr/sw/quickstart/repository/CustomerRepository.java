package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
