package de.othr.sw.quickstart.service;


import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.repository.AccountRepository;
import de.othr.sw.quickstart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class TransactionService implements TransactionServiceIF {
    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    @Qualifier("TransferHandlerCustomer")
    private TransferHandlerIF transferHandlerCustomer;

    @Transactional
    @Override
    public boolean transfer(String senderIban, String receiverIban, Long amount) {
        Date date = java.util.Calendar.getInstance().getTime();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(date);
        return true;
    }
}