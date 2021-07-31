package com.github.zlbovolini.casacodigo.validation.validator;

import com.github.zlbovolini.casacodigo.state.UniqueRelationship;
import com.github.zlbovolini.casacodigo.validation.constraint.UniqueStateInCountry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueStateInCountryValidator implements ConstraintValidator<UniqueStateInCountry, UniqueRelationship> {

    private Class<?> entity;
    private String field;
    private String using;
    private String relationship;
    private String relationshipId;
    private String message;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueStateInCountry constraintAnnotation) {
        entity = constraintAnnotation.entity();
        field = constraintAnnotation.field();
        using = constraintAnnotation.using();
        relationship = constraintAnnotation.relationship();
        relationshipId = constraintAnnotation.relationshipId();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UniqueRelationship uniqueRelationship, ConstraintValidatorContext context) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT count(s) = 0 FROM ")
                .append(entity.getName())
                .append(" s JOIN s.")
                .append(relationship)
                .append(" WHERE s.")
                .append(field)
                .append(" = :name AND s.")
                .append(relationship)
                .append(".")
                .append(relationshipId)
                .append(" = :relationship");

        TypedQuery<Boolean> query = entityManager.createQuery(sb.toString(), Boolean.class);
        query.setParameter("name", uniqueRelationship.getIdentifier());
        query.setParameter("relationship", uniqueRelationship.getRelationship());

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(using)
                .addConstraintViolation();

        return query.getSingleResult();
    }
}
