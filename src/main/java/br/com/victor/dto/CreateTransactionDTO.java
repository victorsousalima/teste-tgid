package br.com.victor.dto;

import br.com.victor.entities.enums.TypeTransaction;

public record CreateTransactionDTO(
    TypeTransaction typeTransaction,
    Long companyID,
    Long clientID,
    Double value
) {

}
