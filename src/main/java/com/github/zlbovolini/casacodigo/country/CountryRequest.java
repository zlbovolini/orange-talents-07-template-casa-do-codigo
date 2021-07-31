package com.github.zlbovolini.casacodigo.country;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zlbovolini.casacodigo.validation.constraint.Unique;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

class CountryRequest {

    @Unique(entity = Country.class, field = "name")
    private final String name;

    @JsonCreator(mode = PROPERTIES)
    CountryRequest(String name) {
        this.name = name;
    }

    Country toModel() {
        return new Country(name);
    }
}
