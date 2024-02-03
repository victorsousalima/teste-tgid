package br.com.victor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
    Client findByCpf(String cpf);
}
