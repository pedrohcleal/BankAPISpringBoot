package com.bankapi.demo.repositories;

import com.bankapi.demo.models.BankModel;
import com.bankapi.demo.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    List<ClientModel> findByBankCNPJ(long bankCNPJ);
    Optional<ClientModel> findByCPFAndBankCNPJ(long cpf, long bankCNPJ);

}
