package br.com.victor.dto;

import br.com.victor.entities.enums.TypeTransaction;

public record NotificationDTO(
    String message,
    TypeTransaction type,
    Double value
) {
    
}
