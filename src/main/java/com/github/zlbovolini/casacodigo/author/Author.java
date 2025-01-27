package com.github.zlbovolini.casacodigo.author;

import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(max = 400)
    private String description;

    private Instant createdAt = Instant.now();

    @Deprecated
    Author() {}

    public Author(@NotBlank String name,
                  @NotBlank @Email String email,
                  @NotBlank @Size(max = 400) String description) {

        ModelUtil.required(name, email, description);

        this.name = name;
        this.email = email;
        this.description = description;
    }

    public Author(Long id,
                  @NotBlank String name,
                  @NotBlank @Email String email,
                  @NotBlank @Size(max = 400) String description,
                  Instant createdAt) {
        this(name, email, description);
        this.id = id;
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
}
