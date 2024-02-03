package br.com.victor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.entities.Company;
import br.com.victor.exceptions.ObjectAlreadyExistsException;
import br.com.victor.repositories.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository repository;

    public Company create(Company company) {
        Company companyExists = repository.findByCnpj(company.getCnpj());

        if (companyExists != null) {
            throw new ObjectAlreadyExistsException("This cnpj is already registered!");
        }

        company = repository.save(company);

        return company;
    }
}
