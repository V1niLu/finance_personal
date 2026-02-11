package com.V1niLu.minhas_financas.controller;

import com.V1niLu.minhas_financas.domain.TransationDomain;
import com.V1niLu.minhas_financas.domain.TransationType;
import com.V1niLu.minhas_financas.dto.TransationResponseDTO;
import com.V1niLu.minhas_financas.service.TransationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transations")
@CrossOrigin("*")
public class TransationController {

    private final TransationService transationService;

    public TransationController(TransationService transationService){
        this.transationService = transationService;
    }

    @GetMapping
    public List<TransationResponseDTO> list(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ){
        return transationService.list(startDate, endDate)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public TransationResponseDTO create(TransationResponseDTO reponseDTO){
        TransationDomain requeste = transationService.create(
                reponseDTO.descricao(),
                reponseDTO.data(),
                reponseDTO.valor(),
                reponseDTO.categoryId(),
                reponseDTO.type()
        );
        return toResponse(requeste);
    }

    @PutMapping("{id}")
    public TransationResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody TransationResponseDTO dto
    ){
        TransationDomain updated = transationService.update(
                id,
                dto.descricao(),
                dto.data(),
                dto.valor(),
                dto.categoryId(),
                dto.type()
        );
        return toResponse(updated);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){ // ✅ era RequestParam
        transationService.delete(id);
    }

    private TransationResponseDTO toResponse(TransationDomain domain) {
        return new TransationResponseDTO(
                domain.getId(),
                domain.getDescricao(),
                domain.getData(),
                domain.getValor(),
                domain.getType(),
                domain.getCategoria() != null ? domain.getCategoria().getId() : null,
                domain.getCategoria() != null ? domain.getCategoria().getName() : null
        );
    }

}
