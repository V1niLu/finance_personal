package com.V1niLu.minhas_financas.controller;

import com.V1niLu.minhas_financas.domain.CategoryDomain;
import com.V1niLu.minhas_financas.dto.CategoryResponseDTO;
import com.V1niLu.minhas_financas.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDTO> list(){
        return categoryService.list().stream().map(this::toResponse).toList();
    }

    @PostMapping
    public CategoryResponseDTO create(@Valid @RequestBody CategoryResponseDTO responseDTO){
        CategoryDomain request = categoryService.create(responseDTO.name());
        return toResponse(request);
    }

    private CategoryResponseDTO toResponse(CategoryDomain categoryDomain) {
        return new CategoryResponseDTO(categoryDomain.getId(), categoryDomain.getName());
    }

}
