package br.com.victor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.dto.CompanyDTO;
import br.com.victor.dto.CreateCompanyDTO;
import br.com.victor.entities.Company;
import br.com.victor.services.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService service;

    @PostMapping()
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody @Valid CreateCompanyDTO createCompany) {
        Company company = service.create(createCompany.convertToCompany());

        return ResponseEntity.status(HttpStatus.CREATED).body(new CompanyDTO(company));
    }
}
