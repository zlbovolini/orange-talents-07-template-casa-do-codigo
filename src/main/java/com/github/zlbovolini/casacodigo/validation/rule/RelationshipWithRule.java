package com.github.zlbovolini.casacodigo.state;

/**
 * Representa uma restrição de relacionamento de entidades.
 * @param <T>
 */
public interface RelationshipWithRule<T> {

    /**
     * Representa o atributo em que a regra de relacionamento RelationshipRule é aplicada.
     * @return valor do atributo em que a regra de relacionamento é aplicada.
     */
    T getRuleAttribute();

    /**
     * Representa o atributo que contém a chave estrangeira do relacionamento.
     * @return valor da chave estrangeira do relacionamento.
     */
    Long getRelationshipId();
}
