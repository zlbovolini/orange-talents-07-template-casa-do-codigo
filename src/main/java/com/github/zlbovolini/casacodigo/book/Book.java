package com.github.zlbovolini.casacodigo.book;

import com.github.zlbovolini.casacodigo.author.Author;
import com.github.zlbovolini.casacodigo.category.Category;
import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String resume;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String summary;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pages;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Future
    @NotNull
    private LocalDate publishedOn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;

    @Deprecated
    Book() {}

    public Book(@NotBlank String title,
                @NotBlank @Size(max = 500) String resume,
                String summary,
                @NotNull @Min(20) BigDecimal price,
                @Min(100) Integer pages,
                @NotBlank String isbn,
                @Future LocalDate publishedOn,
                @NotNull Category category,
                @NotNull Author author) {

        ModelUtil.required(title, resume, price, pages, isbn, category, author);

        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publishedOn = publishedOn;
        this.category = category;
        this.author = author;
    }
}
