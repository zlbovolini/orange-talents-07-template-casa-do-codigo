package com.github.zlbovolini.casacodigo.validation.validator;

import com.github.zlbovolini.casacodigo.validation.rule.RelationshipRule;
import com.github.zlbovolini.casacodigo.validation.rule.RelationshipWithRule;
import com.github.zlbovolini.casacodigo.validation.constraint.ValidRelationship;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRelationshipValidator implements ConstraintValidator<ValidRelationship, RelationshipWithRule<?>> {

    private Class<?> entity;
    private String field;
    private String using;
    private String relationship;
    private String relationshipId;
    private RelationshipRule rule;
    private String message;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ValidRelationship constraintAnnotation) {
        entity = constraintAnnotation.entity();
        field = constraintAnnotation.field();
        using = constraintAnnotation.using();
        relationship = constraintAnnotation.relationship();
        relationshipId = constraintAnnotation.relationshipId();
        rule = constraintAnnotation.rule();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RelationshipWithRule relationshipWithRule, ConstraintValidatorContext context) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT count(s) ")
                .append(rule.getComparator())
                .append(" 0 FROM ")
                .append(entity.getName())
                .append(" s JOIN s.")
                .append(this.relationship)
                .append(" WHERE s.")
                .append(field)
                .append(" = :name AND s.")
                .append(this.relationship)
                .append(".")
                .append(relationshipId)
                .append(" = :relationship");

        TypedQuery<Boolean> query = entityManager.createQuery(sb.toString(), Boolean.class);
        query.setParameter("name", relationshipWithRule.getRuleAttribute());
        query.setParameter("relationship", relationshipWithRule.getRelationshipId());

        String errorMessage = message.isBlank() ? rule.getMessage() : message;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(using)
                .addConstraintViolation();

        return query.getSingleResult();
    }
}
