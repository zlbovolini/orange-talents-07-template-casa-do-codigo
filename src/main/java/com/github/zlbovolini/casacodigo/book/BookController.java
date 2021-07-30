package com.github.zlbovolini.casacodigo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @PersistenceContext
    private final EntityManager entityManager;
    private final BookRepository bookRepository;

    public BookController(EntityManager entityManager, BookRepository bookRepository) {
        this.entityManager = entityManager;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookRequest.toModel(entityManager::find);

        entityManager.persist(book);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<BookResponse> bookResponsePage = bookRepository.findAll(pageable)
                .map(BookResponse::new);

        return ResponseEntity.ok(bookResponsePage);
    }
}
