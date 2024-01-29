package com.bankapi.demo.services;

import com.bankapi.demo.models.BankModel;
import com.bankapi.demo.models.ClientModel;
import com.bankapi.demo.repositories.BankRepository;
import com.bankapi.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ClientRepository clientRepository;

    public BankModel deposit(Long bankCNPJ, Double amount, Long clientCPF) {
        BankModel bank = (BankModel) bankRepository.findByCNPJ(bankCNPJ)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        // Atualizar o saldo do banco
        bank.setBalance(bank.getBalance() + amount);

        // Encontrar o cliente associado ao banco e CPF específico
        ClientModel client = clientRepository.findByCPFAndBankCNPJ(clientCPF, bankCNPJ)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Atualiza o saldo do cliente com o valor depositado
        client.setBalance(client.getBalance() + amount);

        // Salva as alterações no cliente
        clientRepository.save(client);

        // Salva as alterações no banco
        return bankRepository.save(bank);
    }

    public BankModel withdraw(Long bankCNPJ, Double amount, Long clientCPF) {
        BankModel bank = (BankModel) bankRepository.findByCNPJ(bankCNPJ)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        Double tax = bank.getTaxWithdraw();

        // Verificar se o saldo é nulo e, se sim, inicialize com zero
        Double currentBalance = bank.getBalance() != null ? bank.getBalance() : 0.0;

        // Aplicar a taxa ao valor do saque
        Double withdrawnAmount = amount - amount*tax;

        System.out.println("valor saque" + withdrawnAmount);
        System.out.println("tax" + tax);

        // Verificar se há saldo suficiente
        if (currentBalance < withdrawnAmount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Atualizar o saldo do banco
        bank.setBalance(currentBalance - withdrawnAmount);

        // Atualizar o saldo do cliente
        ClientModel client = clientRepository.findByCPFAndBankCNPJ(clientCPF, bankCNPJ)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Descontar a taxa do cliente
        client.setBalance(client.getBalance() - amount*(1+tax));

        // Salvar as alterações no cliente
        clientRepository.save(client);

        // Salvar as alterações no banco
        return bankRepository.save(bank);
    }
}
