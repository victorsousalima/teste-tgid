package br.com.victor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.victor.entities.Client;
import br.com.victor.exceptions.ObjectAlreadyExistsException;
import br.com.victor.exceptions.ObjectNotFoundException;
import br.com.victor.repositories.ClientRepository;

public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    public Client findById(Long id){
        var client = repository.findById(id);

        return client.orElseThrow(() -> new ObjectNotFoundException("Client not found!"));
    }

    @Transactional
    public Client create(Client client) {
        Client clientExists = findById(client.getId());

        if (clientExists != null) {
            throw new ObjectAlreadyExistsException("This client already exists");
        }

        client = repository.save(client);

        return client;
    }
}
