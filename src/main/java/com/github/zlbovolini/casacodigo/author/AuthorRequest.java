package com.github.zlbovolini.casacodigo.author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

class AuthorRequest {

    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Size(max = 400)
    private final String description;

    AuthorRequest(@NotBlank String name,
                  @NotBlank @Email String email,
                  @NotBlank @Size(max = 400) String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorRequest that = (AuthorRequest) o;

        return Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, description);
    }

    Author toModel() {
        return new Author(name, email, description);
    }
}
