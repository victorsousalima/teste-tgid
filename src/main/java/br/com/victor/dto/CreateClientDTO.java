package br.com.victor.dto;

import org.hibernate.validator.constraints.br.CPF;

import br.com.victor.entities.Client;

public record CreateClientDTO(
    @CPF(message = "The cpf is invalid!")
    String cpf
) {
    
    public Client convertToClient() {
        return new Client(null, cpf());
    }
}
