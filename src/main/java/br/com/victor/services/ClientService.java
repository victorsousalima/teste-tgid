package br.com.victor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.victor.entities.Client;
import br.com.victor.exceptions.ObjectAlreadyExistsException;
import br.com.victor.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    @Transactional
    public Client create(Client client) {
        Client clientExists = repository.findByCpf(client.getCpf());

        if (clientExists != null) {
            throw new ObjectAlreadyExistsException("This cpf is already registered!");
        }

        client = repository.save(client);

        return client;
    }

}
