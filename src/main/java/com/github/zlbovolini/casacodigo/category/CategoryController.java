package com.github.zlbovolini.casacodigo.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toModel();

        categoryRepository.save(category);

        return ResponseEntity.ok().build();
    }
}
