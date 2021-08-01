package com.github.zlbovolini.casacodigo.client;

import com.github.zlbovolini.casacodigo.country.Country;
import com.github.zlbovolini.casacodigo.state.State;
import com.github.zlbovolini.casacodigo.util.ModelUtil;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String phone;

    @NotBlank
    private String cep;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @ManyToOne(optional = true)
    private State state;

    @Deprecated
    Client() {}

    Client(String email, String firstName, String lastName, String document,
           String address, String complement, String city, String phone,
           String cep, Country country) {

        ModelUtil.required(email, firstName, lastName, document, address, complement, city, country, phone, city);

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.cep = cep;
    }

    void setState(State state) {
        this.state = state;
    }

    Long getId() {
        return id;
    }
}
