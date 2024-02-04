package br.com.victor.dto;

import java.time.LocalDateTime;

import br.com.victor.entities.Transaction;
import br.com.victor.entities.enums.TypeTransaction;

public record TransactionWithdrawDTO(
    TypeTransaction type,
    Long companyId,
    Long clientId,
    Double valueTransaction,
    Double valueWithTax,
    Double actualValue,
    LocalDateTime realizedAt
) {
    
    public TransactionWithdrawDTO(Transaction transaction) {
        this(
            transaction.getTypeTransaction(),
            transaction.getCompany().getId(),
            transaction.getClient().getId(),
            transaction.getValue(),
            transaction.getValue() * (transaction.getCompany().getSystemRate() / 100.0),
            transaction.getValue() + (transaction.getValue() * (transaction.getCompany().getSystemRate() / 100.0)),
            transaction.getRealizedAt()
            );
    }

}
