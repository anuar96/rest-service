package com.example.restservice.phone;

import com.example.restservice.company.CompanyRepository;
import com.example.restservice.phone.PhoneRepository;
import com.example.restservice.company.Company;
import com.example.restservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies/{companyId}/phones")
    public List<Phone> getPhonesByCompanyId(@PathVariable Long companyId) {
        return phoneRepository.findByCompanyId(companyId);
    }

    @PostMapping("/companies/{companyId}/phones")
    public Phone addPhone(@PathVariable Long companyId,
                            @Valid @RequestBody Phone phone) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    phone.setCompany(company);
                    return phoneRepository.save(phone);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + companyId));
    }

    @PutMapping("/companies/{companyId}/phones/{phoneId}")
    public Phone updatePhone(@PathVariable Long companyId,
                               @PathVariable Long phoneId,
                               @Valid @RequestBody Phone phoneRequest) {
        if(!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with id " + companyId);
        }

        return phoneRepository.findById(phoneId)
                .map(phone -> {
                    phone.setModel(phoneRequest.getModel());
                    return phoneRepository.save(phone);
                }).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + phoneId));
    }

    @DeleteMapping("/companies/{companyId}/phones/{phoneId}")
    public ResponseEntity<?> deletePhone(@PathVariable Long companyId,
                                          @PathVariable Long phoneId) {
        if(!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with id " + companyId);
        }

        return phoneRepository.findById(phoneId)
                .map(phone -> {
                    phoneRepository.delete(phone);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + phoneId));

    }
}
