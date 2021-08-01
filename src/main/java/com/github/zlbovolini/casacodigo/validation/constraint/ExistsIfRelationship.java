package com.github.zlbovolini.casacodigo.validation.constraint;

import com.github.zlbovolini.casacodigo.validation.validator.ExistsIfRelationshipValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsIfRelationshipValidator.class)
public @interface ExistsIfRelationship {

    String message() default "{com.github.zlbovolini.constraints.ExistsIfRelationship}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entity();

    // Field of entity
    String field();

    // Field of annotated class
    String using();

    // Field of entity with relationship
    String relationship();

    // Id field of entity with relationship
    String relationshipId() default "id";
}
