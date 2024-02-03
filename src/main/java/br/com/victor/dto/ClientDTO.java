package br.com.victor.dto;

import br.com.victor.entities.Client;

public record ClientDTO(
    Long id,
    String cpf
) {
    
    public ClientDTO(Client client) {
        this(client.getId(), client.getCpf());
    }
}
