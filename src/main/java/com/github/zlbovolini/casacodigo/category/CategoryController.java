package com.github.zlbovolini.casacodigo.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final UniqueCategoryNameValidator uniqueCategoryNameValidator;

    public CategoryController(CategoryRepository categoryRepository,
                              UniqueCategoryNameValidator uniqueCategoryNameValidator) {
        this.categoryRepository = categoryRepository;
        this.uniqueCategoryNameValidator = uniqueCategoryNameValidator;
    }

    @InitBinder("categoryRequest")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(uniqueCategoryNameValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toModel();

        categoryRepository.save(category);

        return ResponseEntity.ok().build();
    }
}
