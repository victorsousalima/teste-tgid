package br.com.victor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.dto.CreateTransactionDTO;
import br.com.victor.dto.TransactionDepositDTO;
import br.com.victor.dto.TransactionWithdrawDTO;
import br.com.victor.entities.Transaction;
import br.com.victor.entities.enums.TypeTransaction;
import br.com.victor.services.NotificationService;
import br.com.victor.services.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService service;

    @Autowired
    private NotificationService notificationService;


    @PostMapping()
    public ResponseEntity<?> createTransaction(@RequestBody @Valid CreateTransactionDTO createTransaction) {
        Transaction transaction = service.create(createTransaction);

        if (transaction.getTypeTransaction().equals(TypeTransaction.DEPOSIT)) {
            TransactionDepositDTO transactionDepositDTO = new TransactionDepositDTO(transaction);

            notificationService.sendCallback(transaction.getTypeTransaction(), transactionDepositDTO.actualValue());

            notificationService.sendEmail(transaction.getTypeTransaction(), transactionDepositDTO.valueTransaction());

            return ResponseEntity.status(HttpStatus.CREATED).body(transactionDepositDTO);
        }

        else if (transaction.getTypeTransaction().equals(TypeTransaction.WITHDRAW)) {
            TransactionWithdrawDTO transactionWithdrawDTO = new TransactionWithdrawDTO(transaction);

            notificationService.sendCallback(transaction.getTypeTransaction(), transactionWithdrawDTO.actualValue());

            notificationService.sendEmail(transaction.getTypeTransaction(), transactionWithdrawDTO.valueTransaction());

            return ResponseEntity.status(HttpStatus.CREATED).body(transactionWithdrawDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
