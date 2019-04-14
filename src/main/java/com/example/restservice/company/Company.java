package com.example.restservice.company;

import javax.persistence.*;

@Entity
@Table(name = "compnies")
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "Name", length = 64, nullable = false)
    private String Name;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String fullName) {
        this.Name = fullName;
    }
}
