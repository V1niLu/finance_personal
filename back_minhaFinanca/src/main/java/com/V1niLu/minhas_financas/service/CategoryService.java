package com.V1niLu.minhas_financas.service;

import com.V1niLu.minhas_financas.domain.CategoryDomain;
import com.V1niLu.minhas_financas.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<CategoryDomain> list(){
        return repository.findAll();
    }

    @Transactional
    public CategoryDomain create(String name){
        if(repository.findByNameIgnoreCase(name).isPresent()){
            throw new RuntimeException("Categoria já existe");
        }
        CategoryDomain domain = new CategoryDomain();
        domain.setName(name.trim());
        return repository.save(domain);
    }

    public CategoryDomain getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada" + id));
    }
}
