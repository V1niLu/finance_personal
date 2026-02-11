package com.V1niLu.minhas_financas.service;

import com.V1niLu.minhas_financas.domain.CategoryDomain;
import com.V1niLu.minhas_financas.domain.TransationDomain;
import com.V1niLu.minhas_financas.domain.TransationType;
import com.V1niLu.minhas_financas.repository.TransationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransationService {

    CategoryService categoryService;
    TransationRepository categoryRepository;

    TransationService(
            CategoryService categoryService, TransationRepository categoryRepository
    ){

        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;

    }

    public List<TransationDomain> list(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null){
            return categoryRepository.findByDataBetween(startDate, endDate);
        }
        return categoryRepository.findAll();
    }

    @Transactional
    public TransationDomain create(
            String descriptor,
            LocalDate data,
            BigDecimal valor,
            Long categoryId,
            TransationType type
    ){
        TransationDomain domain = new TransationDomain();
        domain.setDescricao(descriptor);
        domain.setData(data);
        domain.setValor(valor);
        domain.setType(type);

        if(categoryId != null){
            domain.setCategoria(categoryService.getCategory(categoryId));
        }

        return categoryRepository.save(domain);
    }

    @Transactional
    public TransationDomain getTransation(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transação não encontrada" + id));
    }

    @Transactional
    public TransationDomain update(
            Long id,
            String descricao,
            LocalDate data,
            BigDecimal valor,
            Long categoryId,
            TransationType type
    ){

        TransationDomain domain = new TransationDomain();
        CategoryDomain transacao = categoryService.getCategory(categoryId);
        domain.setDescricao(descricao);
        domain.setData(data);
        domain.setValor(valor);
        domain.setType(type);

        if(categoryId == null){
            domain.setCategoria(null);
        }else{
            domain.setCategoria(categoryService.getCategory(categoryId));
        }

        return categoryRepository.save(domain);

    }

    @Transactional
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}
