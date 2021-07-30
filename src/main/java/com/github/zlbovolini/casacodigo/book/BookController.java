package com.github.zlbovolini.casacodigo.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookRequest.toModel(entityManager::find);

        entityManager.persist(book);

        return ResponseEntity.ok().build();
    }
}
