package com.bankapi.demo.repositories;

import com.bankapi.demo.models.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<BankModel, UUID> {

}

