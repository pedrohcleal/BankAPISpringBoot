package com.bankapi.demo.dtos;

import jakarta.validation.constraints.NotNull;

public record WithdrawDto(@NotNull Double value,
                          @NotNull long CPF,
                          @NotNull long bankCNPJ) {
}
