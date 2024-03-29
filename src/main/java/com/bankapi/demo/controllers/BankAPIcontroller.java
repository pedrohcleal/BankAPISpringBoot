package com.bankapi.demo.controllers;

import com.bankapi.demo.dtos.*;
import com.bankapi.demo.models.BankModel;
import com.bankapi.demo.models.ClientModel;
import com.bankapi.demo.repositories.BankRepository;
import com.bankapi.demo.repositories.ClientRepository;
import com.bankapi.demo.services.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankAPIcontroller {
    @Autowired
    BankRepository bankRepository;

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/bank/create")
    public ResponseEntity<BankModel> saveBank(@RequestBody @Valid BankRecordDto bankRecordDto){
        var bankModel = new BankModel();
        BeanUtils.copyProperties(bankRecordDto, bankModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankRepository.save(bankModel));
    }

    @PostMapping("/client/create")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDto clientRecordDto){
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }
    @GetMapping("/banks")
    public ResponseEntity<List<BankModel>> getAllBanks(){
        return ResponseEntity.status(HttpStatus.OK).body(bankRepository.findAll());
    }
    @PostMapping("/transaction")
    public ResponseEntity<String> performTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        try {
            // Verificar se os CPFs e CNPJ estão presentes no banco de dados
            Optional<ClientModel> senderOptional = clientRepository.findByCPFAndBankCNPJ(transactionDto.CPF(), Long.parseLong(String.valueOf(transactionDto.CNPJ())));
            Optional<ClientModel> receiverOptional = clientRepository.findByCPFAndBankCNPJ(transactionDto.CPF2(), Long.parseLong(String.valueOf(transactionDto.CNPJ())));

            if (senderOptional.isPresent() && receiverOptional.isPresent()) {
                // Verificar se o cliente tem saldo suficiente para a transação
                ClientModel sender = senderOptional.get();
                ClientModel receiver = receiverOptional.get();

                if (sender.getBalance() >= transactionDto.value()) {
                    // Realizar a transação
                    sender.setBalance(sender.getBalance() - transactionDto.value());
                    receiver.setBalance(receiver.getBalance() + transactionDto.value());

                    clientRepository.save(sender);
                    clientRepository.save(receiver);

                    return ResponseEntity.ok("Transação realizada com sucesso.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente para a transação.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a transação.");
        }
    }

    @Autowired
    private BankService bankService;

    @PostMapping("/deposit")
    public ResponseEntity<BankModel> deposit(@RequestBody @Valid DepositDto depositDTO) {
        BankModel updatedBank = bankService.deposit(depositDTO.bankCNPJ(), depositDTO.value(), depositDTO.CPF());
        return ResponseEntity.ok(updatedBank);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankModel> withdraw(@RequestBody @Valid WithdrawDto withdrawDTO) {
        BankModel updatedBank = bankService.withdraw(withdrawDTO.bankCNPJ(), withdrawDTO.value(), withdrawDTO.CPF());
        return ResponseEntity.ok(updatedBank);
    }

    @DeleteMapping("/{bankCNPJ}/{cpf}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "bankCNPJ") long bankCNPJ,
                                               @PathVariable(value = "cpf") long cpf){
        System.out.println(bankCNPJ);
        System.out.println(cpf);
        Optional<ClientModel> client0 = clientRepository.findByCPFAndBankCNPJ(cpf, bankCNPJ);
        if (client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        } else if (client0.get().getBalance() == 0) {
            clientRepository.delete(client0.get());
            return ResponseEntity.status(HttpStatus.OK).body("Client deleted sucessfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client has money, balance of client = "
                + client0.get().getBalance());
    }
}
