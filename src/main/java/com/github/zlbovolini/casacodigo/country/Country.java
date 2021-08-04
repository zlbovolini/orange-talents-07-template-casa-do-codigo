package com.github.zlbovolini.casacodigo.country;

import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Deprecated
    Country() {}

    Country(@NotBlank String name) {
        ModelUtil.required(name);
        this.name = name;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
