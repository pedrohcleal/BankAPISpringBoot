package com.bankapi.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDto(@NotNull long CPF,
                             @NotNull long CPF2,
                             @NotNull float value,
                             @NotNull long CNPJ) {

}
