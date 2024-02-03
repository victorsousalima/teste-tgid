package br.com.victor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
