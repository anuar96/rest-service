package com.example.restservice.com.example.restservice.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreResource {
    @Autowired
    private GenreRepository genreRepository;
    @GetMapping("/genres")
    public List<Genre> retrieveAllGenres() {
        return genreRepository.findAll();
    }
}
