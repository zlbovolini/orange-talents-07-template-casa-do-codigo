package com.github.zlbovolini.casacodigo.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zlbovolini.casacodigo.validation.constraint.Unique;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CategoryRequest {

    @NotBlank
    @Unique(entity = Category.class, field = "name")
    private final String name;

    @JsonCreator
    CategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryRequest that = (CategoryRequest) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    Category toModel() {
        return new Category(name);
    }
}
