package com.V1niLu.minhas_financas.repository;

import com.V1niLu.minhas_financas.domain.TransationDomain;
import com.V1niLu.minhas_financas.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransationRepository extends JpaRepository<TransationDomain, Long> {

    // Busca todas as transações de um usuário
    List<TransationDomain> findByUser(UserDomain user);

    List<TransationDomain> findByDataBetween(LocalDate startDate, LocalDate endDate);
}
