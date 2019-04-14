package com.example.restservice.phone;

import com.example.restservice.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "Name", length = 64, nullable = false)
    private String Model;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getModel() {
        return Model;
    }
    public void setModel(String fullName) {
        this.Model = fullName;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company CompanyName) {
        this.company = CompanyName;
    }
}
