package com.github.zlbovolini.casacodigo.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zlbovolini.casacodigo.country.Country;
import com.github.zlbovolini.casacodigo.state.State;
import com.github.zlbovolini.casacodigo.validation.constraint.CPFOrCNPJ;
import com.github.zlbovolini.casacodigo.validation.constraint.Exists;
import com.github.zlbovolini.casacodigo.validation.constraint.ExistsIfRelationship;
import com.github.zlbovolini.casacodigo.validation.constraint.Unique;
import com.github.zlbovolini.casacodigo.validation.rule.RelationshipWithRule;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

@ExistsIfRelationship(entity = State.class, field = "id", using = "stateId", relationship = "country")
class ClientRequest implements RelationshipWithRule<Long> {

    @NotBlank
    @Email
    @Unique(entity = Client.class, field = "email")
    private final String email;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @NotBlank
    @CPFOrCNPJ
    @Unique(entity = Client.class, field = "document")
    private final String document;

    @NotBlank
    private final String address;

    @NotBlank
    private final String complement;

    @NotBlank
    private final String city;

    @NotBlank
    private final String phone;

    @NotBlank
    private final String cep;

    @NotNull
    @Exists(entity = Country.class)
    private final Long countryId;

    private Long stateId;

    @JsonCreator(mode = PROPERTIES)
    ClientRequest(String email, String firstName, String lastName, String document,
                  String address, String complement, String city, Long countryId,
                  String phone, String cep) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.phone = phone;
        this.cep = cep;
    }

    public Long getStateId() {
        return stateId;
    }

    void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public Long getRuleAttribute() {
        return stateId;
    }

    @Override
    public Long getRelationshipId() {
        return countryId;
    }

    Client toModel(@NotNull BiFunction<Class<?>, Object, ?> find) {

        Country country = Optional.of((Country) find.apply(Country.class, countryId))
                .orElseThrow();

        Client client = new Client(email, firstName, lastName, document, address, complement, city, phone, cep, country);

        if (Objects.nonNull(stateId)) {
            State state = Optional.of((State) find.apply(State.class, stateId))
                    .orElseThrow();
            client.setState(state);
        }

        return client;
    }
}


