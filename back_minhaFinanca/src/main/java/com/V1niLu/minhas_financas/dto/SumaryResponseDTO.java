package com.V1niLu.minhas_financas.dto;

import java.math.BigDecimal;

public record SumaryResponseDTO(
        BigDecimal renda,
        BigDecimal despesa,
        BigDecimal divida,
        BigDecimal saldo
) {}
