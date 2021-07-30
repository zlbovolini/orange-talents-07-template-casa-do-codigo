package com.github.zlbovolini.casacodigo.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zlbovolini.casacodigo.author.Author;
import com.github.zlbovolini.casacodigo.category.Category;
import com.github.zlbovolini.casacodigo.validation.constraint.Exists;
import com.github.zlbovolini.casacodigo.validation.constraint.Unique;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class BookRequest {

    @NotBlank
    @Unique(entity = Book.class, field = "title")
    private String title;

    @NotBlank
    @Size(max = 500)
    private String resume;

    //@Size(max = 10000)
    @NotBlank
    private String summary;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pages;

    @NotBlank
    @Unique(entity = Book.class, field = "isbn")
    private String isbn;

    @Future
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate publishedOn;

    @NotNull
    @Exists(entity = Category.class)
    private Long categoryId;

    @NotNull
    @Exists(entity = Author.class)
    private Long authorId;

    @JsonCreator(mode = PROPERTIES)
    public BookRequest(String title, String resume, String summary, BigDecimal price,
                       Integer pages, String isbn, LocalDate publishedOn,
                       Long categoryId, Long authorId) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publishedOn = publishedOn;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    public Book toModel(BiFunction<Class<?>, Object, ?> find) {

        Category category = (Category) find.apply(Category.class, categoryId);
        Author author = (Author) find.apply(Author.class, authorId);

        return new Book(title, resume, summary, price, pages, isbn, publishedOn, category, author);
    }

}
