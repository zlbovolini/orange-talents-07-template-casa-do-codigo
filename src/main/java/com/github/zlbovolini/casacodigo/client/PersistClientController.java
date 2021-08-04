package com.github.zlbovolini.casacodigo.client;

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
@RequestMapping("/api/v1/clients")
public class PersistClientController {

    @PersistenceContext
    private EntityManager entityManager;

    public PersistClientController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientRequest.toModel(entityManager::find);

        entityManager.persist(client);
        ClientResponse clientResponse = new ClientResponse(client);

        return ResponseEntity.ok(clientResponse);
    }
}
