package com.github.zlbovolini.casacodigo.author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Size(max = 400)
    private final String description;

    private final Instant createdAt;

    public Author(@NotBlank String name,
                  @NotBlank @Email String email,
                  @NotBlank @Size(max = 400) String description) {
        // Throw IllegalArgumentException if some argument is invalid
        checkArguments(Arrays.asList(name, email, description));

        this.name = name;
        this.email = email;
        this.description = description;
        this.createdAt = Instant.now();
    }

    public Author(Long id,
                  @NotBlank String name,
                  @NotBlank @Email String email,
                  @NotBlank @Size(max = 400) String description,
                  Instant createdAt) {
        // Throw IllegalArgumentException if some parameter is invalid
        checkArguments(Arrays.asList(name, email, description));

        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    private void checkArguments(List<String> arguments) {
        boolean hasInconsistency = arguments.stream()
                .anyMatch(argument -> Objects.isNull(argument) || argument.isBlank());

        if (hasInconsistency) {
            throw new IllegalArgumentException("There is some invalid value");
        }
    }
}
