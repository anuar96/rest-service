package com.example.restservice.company;

import com.example.restservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public Page<Company> getQuestions(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/companies/{companyId}")
    public Company updateCompany(@PathVariable Long companyId,
                                   @Valid @RequestBody Company companyRequest) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    company.setName(companyRequest.getName());
                    return companyRepository.save(company);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + companyId));
    }


    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    companyRepository.delete(company);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + companyId));
    }
}
