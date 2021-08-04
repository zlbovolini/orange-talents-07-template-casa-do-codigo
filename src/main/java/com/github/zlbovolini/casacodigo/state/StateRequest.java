package com.github.zlbovolini.casacodigo.state;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zlbovolini.casacodigo.country.Country;
import com.github.zlbovolini.casacodigo.validation.constraint.Exists;
import com.github.zlbovolini.casacodigo.validation.constraint.UniqueRelationship;
import com.github.zlbovolini.casacodigo.validation.rule.RelationshipWithRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

@UniqueRelationship(entity = State.class, field = "name", using = "name", relationship = "country")
public class StateRequest implements RelationshipWithRule<String> {

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
    public String getRuleAttribute() {
        return name;
    }

    @Override
    public Long getRelationshipId() {
        return countryId;
    }

}
