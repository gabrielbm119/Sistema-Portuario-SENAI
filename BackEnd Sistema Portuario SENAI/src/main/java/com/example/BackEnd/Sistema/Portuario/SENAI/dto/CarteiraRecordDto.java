package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CarteiraRecordDto(
        @NotNull(message = "O saldo é obrigatório")
        @PositiveOrZero(message = "O saldo não pode ser negativo")
        BigDecimal sdCarteira,

        @NotNull(message = "O ID do cliente é obrigatório")
        Integer idCliente
) {}