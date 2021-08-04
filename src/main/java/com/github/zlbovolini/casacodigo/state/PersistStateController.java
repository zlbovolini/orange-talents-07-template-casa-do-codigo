package com.github.zlbovolini.casacodigo.state;

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
@RequestMapping("/api/v1/states")
public class PersistStateController {

    @PersistenceContext
    private final EntityManager entityManager;

    public PersistStateController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody StateRequest stateRequest) {
        State state = stateRequest.toModel(entityManager::find);

        entityManager.persist(state);

        return ResponseEntity.ok().build();
    }
}
