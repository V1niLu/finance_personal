package com.V1niLu.minhas_financas.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreatRequestDTO(
        @NotBlank String name
) {}
