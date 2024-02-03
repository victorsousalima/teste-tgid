package br.com.victor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.dto.ClientDTO;
import br.com.victor.dto.CreateClientDTO;
import br.com.victor.entities.Client;
import br.com.victor.services.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientService service;

    @PostMapping()
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid CreateClientDTO createClient) {
        Client client = service.create(createClient.convertToClient());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(client));
    }
}
