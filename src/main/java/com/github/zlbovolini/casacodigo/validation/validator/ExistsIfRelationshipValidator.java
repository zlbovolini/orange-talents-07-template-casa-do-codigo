package com.github.zlbovolini.casacodigo.validation.validator;

import com.github.zlbovolini.casacodigo.validation.constraint.ExistsIfRelationship;
import com.github.zlbovolini.casacodigo.validation.rule.RelationshipWithRule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIfRelationshipValidator implements ConstraintValidator<ExistsIfRelationship, RelationshipWithRule<?>> {

    private Class<?> entity;
    private String field;
    private String using;
    private String relationship;
    private String relationshipId;
    private String message;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsIfRelationship constraintAnnotation) {
        entity = constraintAnnotation.entity();
        field = constraintAnnotation.field();
        using = constraintAnnotation.using();
        relationship = constraintAnnotation.relationship();
        relationshipId = constraintAnnotation.relationshipId();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RelationshipWithRule relationshipWithRule, ConstraintValidatorContext context) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT count(*) > 0 FROM ")
                .append(entity.getName())
                .append(" s RIGHT JOIN s.")
                .append(relationship)
                .append(" c WHERE ((s.")
                .append(field)
                .append(" = :id OR :id is NULL ) AND c.")
                .append(relationshipId)
                .append(" = :relationship)");

        TypedQuery<Boolean> query = entityManager.createQuery(sb.toString(), Boolean.class);
        query.setParameter("id", relationshipWithRule.getRuleAttribute());
        query.setParameter("relationship", relationshipWithRule.getRelationshipId());

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(using)
                .addConstraintViolation();

        return query.getSingleResult();
    }
}
