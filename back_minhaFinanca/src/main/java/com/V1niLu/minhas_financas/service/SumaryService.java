package com.V1niLu.minhas_financas.service;

import com.V1niLu.minhas_financas.domain.TransationDomain;
import com.V1niLu.minhas_financas.dto.SumaryResponseDTO;
import com.V1niLu.minhas_financas.repository.TransationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SumaryService {

    TransationRepository transationRepository;

    SumaryService(TransationRepository repository){
        this.transationRepository = repository;
    }

    public SumaryResponseDTO compute() {

        List<TransationDomain> allTransation = transationRepository.findAll();

        BigDecimal renda = BigDecimal.ZERO;
        BigDecimal despesa = BigDecimal.ZERO;
        BigDecimal divida = BigDecimal.ZERO;

        for (TransationDomain transation : allTransation) {
            if (transation.getType().equals(renda)) renda = renda.add(transation.getValor());
            if (transation.getType().equals(despesa)) despesa = despesa.add(transation.getValor());
            if (transation.getType().equals(divida)) divida = divida.add(transation.getValor());
        }

        BigDecimal saldo = renda.subtract(despesa).subtract(divida);
        return new SumaryResponseDTO(renda, despesa, divida, saldo);
    }
}
