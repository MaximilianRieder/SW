package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    //Optional<Transaction> findById(String id);
    Page<Transaction> findAllByReceiver_id(Long id, Pageable pageable);
}
