package com.github.zlbovolini.casacodigo.author;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final UniqueAuthorEmailValidator uniqueAuthorEmailValidator;

    public AuthorController(AuthorRepository authorRepository, UniqueAuthorEmailValidator uniqueAuthorEmailValidator) {
        this.authorRepository = authorRepository;
        this.uniqueAuthorEmailValidator = uniqueAuthorEmailValidator;
    }

    @InitBinder("authorRequest")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(uniqueAuthorEmailValidator);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AuthorRequest authorRequest) {
        Author author = authorRequest.toModel();

        authorRepository.save(author);

        return ResponseEntity.ok().build();
    }
}
