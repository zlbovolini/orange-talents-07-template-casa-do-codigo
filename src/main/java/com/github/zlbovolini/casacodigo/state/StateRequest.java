package com.github.zlbovolini.casacodigo.state;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zlbovolini.casacodigo.country.Country;
import com.github.zlbovolini.casacodigo.validation.constraint.Exists;
import com.github.zlbovolini.casacodigo.validation.constraint.UniqueStateInCountry;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

@UniqueStateInCountry(entity = State.class, using = "name")
public class StateRequest implements UniqueRelationship {

    @NotBlank
    private final String name;

    @NotNull
    @Exists(entity = Country.class)
    private final Long countryId;

    @JsonCreator(mode = PROPERTIES)
    StateRequest(@NotBlank String name, @NotNull Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    State toModel(BiFunction<Class<?>, Object, ?> find) {
        Country country = Optional.ofNullable((Country) find.apply(Country.class, countryId))
                .orElseThrow();

        return new State(name, country);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getIdentifier() {
        return name;
    }

    @Override
    public Long getRelationship() {
        return countryId;
    }

}
