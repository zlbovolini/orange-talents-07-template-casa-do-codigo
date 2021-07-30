package com.github.zlbovolini.casacodigo.category;

import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
        ModelUtil.required();

        this.name = name;
    }

    Category(Long id,
             @NotBlank String name) {
        this(name);
        this.id = id;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
