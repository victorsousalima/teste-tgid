package br.com.victor.dto;

import br.com.victor.entities.enums.TypeTransaction;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionDTO(
    @NotNull
    TypeTransaction typeTransaction,
    @NotNull
    Long companyID,
    @NotNull
    Long clientID,
    @NotNull
    Double value
) {

}
