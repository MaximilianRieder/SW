package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByIban(String iban);
    List<Account> findByAccountHolder_Username(String username);
}
