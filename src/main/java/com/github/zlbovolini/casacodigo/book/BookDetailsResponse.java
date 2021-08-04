package com.github.zlbovolini.casacodigo.book;

import com.github.zlbovolini.casacodigo.author.AuthorDetailsResponse;
import com.github.zlbovolini.casacodigo.category.CategoryDetailsResponse;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDetailsResponse {

    private final Long id;
    private final String title;
    private final String resume;
    private final String summary;
    private final BigDecimal price;
    private final Integer pages;
    private final String isbn;
    private final LocalDate publishedOn;

    @NotNull
    private final AuthorDetailsResponse author;
    @NotNull
    private final CategoryDetailsResponse category;

    BookDetailsResponse(@NotNull Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publishedOn = book.getPublishedOn();
        this.author = new AuthorDetailsResponse(book.getAuthor());
        this.category = new CategoryDetailsResponse(book.getCategory());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public AuthorDetailsResponse getAuthor() {
        return author;
    }

    public CategoryDetailsResponse getCategory() {
        return category;
    }
}
