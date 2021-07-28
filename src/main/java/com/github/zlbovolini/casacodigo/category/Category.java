package com.github.zlbovolini.casacodigo.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Deprecated
    Category() {}

    Category(@NotBlank String name) {
        // Throw IllegalArgumentException if some argument is invalid

        this.name = name;
    }

    Category(Long id,
             @NotBlank String name) {
        this(name);
        this.id = id;
    }
}
