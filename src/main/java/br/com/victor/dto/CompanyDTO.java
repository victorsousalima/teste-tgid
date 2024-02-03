package br.com.victor.dto;

import br.com.victor.entities.Company;

public record CompanyDTO(
    Long id,
    String cnpj,
    Double balance,
    Double systemRate
) {
    
    public CompanyDTO(Company company) {
        this(company.getId(), company.getCnpj(), company.getBalance(), company.getSystemRate());
    }

}
