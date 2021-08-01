package com.github.zlbovolini.casacodigo.client;

class ClientResponse {

    private final Long id;

    ClientResponse(Client client) {
        this.id = client.getId();
    }

    public Long getId() {
        return id;
    }
}
