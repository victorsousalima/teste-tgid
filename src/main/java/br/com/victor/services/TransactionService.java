package br.com.victor.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.victor.dto.CreateTransactionDTO;
import br.com.victor.entities.Client;
import br.com.victor.entities.Company;
import br.com.victor.entities.Transaction;
import br.com.victor.entities.enums.TypeTransaction;
import br.com.victor.exceptions.ObjectNotFoundException;
import br.com.victor.exceptions.ValueTransactionInvalidException;
import br.com.victor.repositories.ClientRepository;
import br.com.victor.repositories.CompanyRepository;
import br.com.victor.repositories.TransactionRepository;

public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Transaction create(CreateTransactionDTO createTransaction) {
        if (createTransaction.value() <= 0) {
            throw new ValueTransactionInvalidException("The transaction value cannot be 0 or negative!");
        }

        Company company = companyRepository.getReferenceById(createTransaction.companyID());

        Client client = clientRepository.getReferenceById(createTransaction.clientID());

        if (company == null) {
            throw new ObjectNotFoundException("This company doesn't exist!");
        }

        if (client == null) {
            throw new ObjectNotFoundException("This client doesn't exist!");
        }

        if (createTransaction.typeTransaction().equals(TypeTransaction.DEPOSIT)) {
            deposit(createTransaction, company);
        }
        else if (createTransaction.typeTransaction().equals(TypeTransaction.WITHDRAW)) {
            withdraw(createTransaction, company);
        }

        Transaction transaction = new Transaction(null,createTransaction.typeTransaction(), company, client, createTransaction.value(), LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    private void deposit(CreateTransactionDTO dataTransaction, Company company) {
        Double tax = dataTransaction.value() * (company.getSystemRate() / 100.0);
        Double newBalanceCompany = company.getBalance() + (dataTransaction.value() - tax);

        company.setBalance(newBalanceCompany);
    }

    @Transactional
    private void withdraw(CreateTransactionDTO dataTransaction, Company company) {
        Double tax = dataTransaction.value() * (company.getSystemRate() / 100.0);
        Double newBalanceCompany = company.getBalance() - (dataTransaction.value() + tax);

        company.setBalance(newBalanceCompany);
    }
}
