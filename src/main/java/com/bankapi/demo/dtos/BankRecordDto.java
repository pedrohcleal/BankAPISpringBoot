package com.bankapi.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BankRecordDto(@NotBlank String name,
                            @NotNull long CNPJ,
                            @NotNull Double tax) {
}