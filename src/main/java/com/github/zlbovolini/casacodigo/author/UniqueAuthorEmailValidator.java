package com.github.zlbovolini.casacodigo.author;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class UniqueAuthorEmailValidator implements Validator {

    private final AuthorRepository authorRepository;

    public UniqueAuthorEmailValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        AuthorRequest authorRequest = (AuthorRequest) object;
        String email = authorRequest.getEmail();

        if (Objects.isNull(email) || email.isBlank()) {
            return;
        }

        Optional<Author> optionalAuthor = authorRepository.findByEmail(email);

        if (optionalAuthor.isPresent()) {
            errors.rejectValue("email", "NotUniqueEmail");
        }
    }
}
