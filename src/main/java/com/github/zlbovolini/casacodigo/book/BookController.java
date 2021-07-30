package com.github.zlbovolini.casacodigo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> findAll(Pageable pageable) {
        Page<BookResponse> bookResponsePage = bookRepository.findAll(pageable)
                .map(BookResponse::new);

        return ResponseEntity.ok(bookResponsePage);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDetailsResponse> find(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> ResponseEntity.ok(new BookDetailsResponse(book)))
                .orElse(ResponseEntity.notFound().build());
    }
}
