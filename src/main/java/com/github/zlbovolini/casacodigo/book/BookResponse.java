package com.github.zlbovolini.casacodigo.book;

class BookResponse {

    private final Long id;
    private final String title;

    BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
