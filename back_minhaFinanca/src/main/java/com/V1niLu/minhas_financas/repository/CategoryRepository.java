package com.V1niLu.minhas_financas.repository;

import com.V1niLu.minhas_financas.domain.CategoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryDomain, Long> {

    Optional<CategoryRepository> findByNameIgnoreCase(String name);
    // O método findByNameIgnoreCase é uma convenção do Spring Data JPA que permite buscar uma categoria pelo nome, ignorando diferenças de maiúsculas e minúsculas. Ele retorna um Optional<CategoryRepository>, indicando que a categoria pode ou não ser encontrada.

}
