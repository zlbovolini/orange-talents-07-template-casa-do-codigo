package com.github.zlbovolini.casacodigo.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class UniqueCategoryNameValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public UniqueCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        CategoryRequest categoryRequest = (CategoryRequest) object;
        String name = categoryRequest.getName();

        if (Objects.isNull(name) || name.isBlank()) {
            return;
        }

        Optional<Category> optionalCategory = categoryRepository.findByName(name);

        if (optionalCategory.isPresent()) {
            errors.rejectValue("name", "NotUnique");
        }
    }
}
