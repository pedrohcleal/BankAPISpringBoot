package com.bankapi.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClientRecordDto(@NotBlank String name,
                              @NotNull long CPF,
                              @NotNull long bankCNPJ,
                              double balance) {

}
