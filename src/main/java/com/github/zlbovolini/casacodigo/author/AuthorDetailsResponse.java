package com.github.zlbovolini.casacodigo.author;

import javax.validation.constraints.NotNull;

public class AuthorDetailsResponse {

    private final Long id;
    private final String nome;

    public AuthorDetailsResponse(@NotNull Author author) {
        this.id = author.getId();
        this.nome = author.getName();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
