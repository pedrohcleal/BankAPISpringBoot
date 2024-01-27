package com.bankapi.demo.repositories;

import com.bankapi.demo.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    Optional<ClientModel> findByCPFAndBankCNPJ(long cpf, long bankCNPJ);
}
