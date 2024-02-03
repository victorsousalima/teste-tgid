package br.com.victor.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.victor.entities.Company;
import jakarta.validation.constraints.NotNull;

public record CreateCompanyDTO(
    @CNPJ(message = "This cnpj is invalid!")
    String cnpj,
    @NotNull
    Double balance,
    @NotNull
    Double systemRate
) {

    public Company convertToCompany() {
        return new Company(null, cnpj(), balance(), systemRate());
    }
    
}
