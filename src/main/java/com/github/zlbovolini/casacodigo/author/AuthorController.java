package com.github.zlbovolini.casacodigo.author;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AuthorRequest authorRequest) {
        Author author = authorRequest.toModel();

        authorRepository.save(author);

        return ResponseEntity.ok().build();
    }
}
