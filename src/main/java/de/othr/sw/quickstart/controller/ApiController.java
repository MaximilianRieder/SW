package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.dtos.TransactionRequestDto;
import de.othr.sw.quickstart.dtos.TransactionReturnDto;
import de.othr.sw.quickstart.entity.Transaction;
import de.othr.sw.quickstart.entity.TransactionStatus;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import de.othr.sw.quickstart.service.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Scope("session")
@RequestMapping("/restapi")
public class ApiController {
    @Autowired
    TransactionServiceIF transactionService;
    @Autowired
    CustomerServiceIF customerService;
    @Autowired
    AccountServiceIF accountService;

    @RequestMapping(value="/transaction", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public TransactionReturnDto transfer(@RequestBody TransactionRequestDto requestDto) {
        TransactionReturnDto returnDto = new TransactionReturnDto();

        //check if key matches with account and if UUID has right length
        if(requestDto.getSenderKey().length() != 36 || (!(accountService.verifyAccount(requestDto.getSenderIban(), requestDto.getSenderKey())))) {
            returnDto.setId(-1);
            returnDto.setMessage("authorisation failure");
            returnDto.setStatus(false);
            return  returnDto;
        }

        //create transaction
        Optional<Transaction> transO = transactionService.transfer(requestDto.getSenderIban(), requestDto.getReceiverIban(), requestDto.getAmount());
        if(transO.isEmpty()) {
            returnDto.setId(-1);
            returnDto.setMessage("false iban");
            returnDto.setStatus(false);
        } else {
            if(transO.get().getStatus() == TransactionStatus.SUCCESS) {
                returnDto.setId(transO.get().getId());
                returnDto.setMessage("success");
                returnDto.setStatus(true);
            } else {
                returnDto.setId(transO.get().getId());
                returnDto.setMessage("not enough money in account");
                returnDto.setStatus(false);
            }
        }
        return returnDto;
    }
}
