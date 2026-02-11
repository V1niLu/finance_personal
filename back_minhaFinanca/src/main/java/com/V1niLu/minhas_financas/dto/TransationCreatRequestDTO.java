package com.V1niLu.minhas_financas.dto;

import com.V1niLu.minhas_financas.domain.TransationType;

public record TransationCreatRequestDTO(
        String description,
        Double value,
        TransationType type,
        Long categoryId
) {
}
