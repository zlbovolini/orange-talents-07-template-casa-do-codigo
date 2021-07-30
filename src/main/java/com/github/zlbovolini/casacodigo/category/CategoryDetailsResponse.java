package com.github.zlbovolini.casacodigo.category;

import javax.validation.constraints.NotNull;

public class CategoryDetailsResponse {

    private final Long id;
    private final String name;

    public CategoryDetailsResponse(@NotNull Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
