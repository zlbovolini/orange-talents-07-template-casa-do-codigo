package com.github.zlbovolini.casacodigo.state;

import com.github.zlbovolini.casacodigo.country.Country;
import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @Deprecated
    State() {}

    State(@NotBlank String name, @NotNull Country country) {
        ModelUtil.required(name, country);
        this.name = name;
        this.country = country;
    }
}
