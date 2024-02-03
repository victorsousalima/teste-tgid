package br.com.victor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    
    Company findByCnpj(String cnpj);
}
