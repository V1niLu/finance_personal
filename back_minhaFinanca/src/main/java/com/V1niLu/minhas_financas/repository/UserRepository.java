package com.V1niLu.minhas_financas.repository;

import com.V1niLu.minhas_financas.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDomain, Long> {

    Optional<UserDomain> findByEmailIgnoreCase(String email);

}
