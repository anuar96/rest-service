package com.example.restservice.notebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long>{
    List<Notebook> findByCompanyId(Long companyId);
}
