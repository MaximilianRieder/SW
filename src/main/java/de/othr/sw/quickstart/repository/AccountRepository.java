package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Account;
import de.othr.sw.quickstart.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByIban(String iban);
}
