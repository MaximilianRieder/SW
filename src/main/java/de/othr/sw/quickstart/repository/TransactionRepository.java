package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
