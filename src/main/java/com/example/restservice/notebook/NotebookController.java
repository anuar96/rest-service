package com.example.restservice.notebook;

import com.example.restservice.company.CompanyRepository;
import com.example.restservice.notebook.NotebookRepository;
import com.example.restservice.company.Company;
import com.example.restservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class NotebookController {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies/{companyId}/notebooks")
    public List<Notebook> getNotebooksByCompanyId(@PathVariable Long companyId) {
        return notebookRepository.findByCompanyId(companyId);
    }

    @PostMapping("/companies/{companyId}/notebooks")
    public Notebook addNotebook(@PathVariable Long companyId,
                          @Valid @RequestBody Notebook notebook) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    notebook.setCompany(company);
                    return notebookRepository.save(notebook);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + companyId));
    }

    @PutMapping("/companies/{companyId}/notebooks/{notebookId}")
    public Notebook updateNotebook(@PathVariable Long companyId,
                             @PathVariable Long notebookId,
                             @Valid @RequestBody Notebook notebookRequest) {
        if(!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with id " + companyId);
        }

        return notebookRepository.findById(notebookId)
                .map(notebook -> {
                    notebook.setModel(notebookRequest.getModel());
                    return notebookRepository.save(notebook);
                }).orElseThrow(() -> new ResourceNotFoundException("Notebook not found with id " + notebookId));
    }

    @DeleteMapping("/companies/{companyId}/notebooks/{notebookId}")
    public ResponseEntity<?> deleteNotebook(@PathVariable Long companyId,
                                         @PathVariable Long notebookId) {
        if(!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with id " + companyId);
        }

        return notebookRepository.findById(notebookId)
                .map(notebook -> {
                    notebookRepository.delete(notebook);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Notebook not found with id " + notebookId));

    }
}