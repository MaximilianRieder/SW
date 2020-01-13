package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Transaction;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    //Optional<Transaction> findById(String id);
    List<Transaction> findByReceiver_Id( Long id);
    List<Transaction> findBySender_Id(Long id);
    Page<Transaction> findByReceiver_IdAndSender_Id(Long id1, Long id2, Pageable pageable);
}
