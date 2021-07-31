package com.github.zlbovolini.casacodigo.country;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/countries")
public class PersistCountryController {

    @PersistenceContext
    private final EntityManager entityManager;

    public PersistCountryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody CountryRequest countryRequest) {
        Country country = countryRequest.toModel();

        entityManager.persist(country);

        return ResponseEntity.ok().build();
    }
}
