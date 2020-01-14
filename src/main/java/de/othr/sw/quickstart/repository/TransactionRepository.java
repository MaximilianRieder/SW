package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Transaction;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    //Optional<Transaction> findById(String id);
    List<Transaction> findByReceiver_Id(long id);
    List<Transaction> findBySender_Id(long id);
    Page<Transaction> findByReceiver_IdOrSender_Id(long id1, long id2, Pageable pageable);
}
