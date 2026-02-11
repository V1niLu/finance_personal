package com.V1niLu.minhas_financas.dto;

import com.V1niLu.minhas_financas.domain.TransationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransationResponseDTO(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        TransationType type,
        Long categoryId,
        String categoryName
) {
}
