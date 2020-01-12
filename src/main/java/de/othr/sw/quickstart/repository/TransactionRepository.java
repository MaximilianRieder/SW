package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findByTID(String tID);
}
