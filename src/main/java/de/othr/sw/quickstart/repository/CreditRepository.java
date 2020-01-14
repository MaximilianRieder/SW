package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Credit;
import de.othr.sw.quickstart.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreditRepository extends CrudRepository<Credit, Long> {
}
