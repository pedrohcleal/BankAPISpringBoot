package com.bankapi.demo.dtos;

import jakarta.validation.constraints.NotNull;

public record DepositDto(@NotNull Double value,
                         @NotNull long CPF,
                         @NotNull long bankCNPJ) {
}
