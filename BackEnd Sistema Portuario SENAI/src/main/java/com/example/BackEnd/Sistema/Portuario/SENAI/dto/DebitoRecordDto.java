package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DebitoRecordDto(
        @NotNull(message = "O valor do débito é obrigatório")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal vlDebito,

        @NotNull(message = "A data de validade é obrigatória")
        LocalDateTime dvDebito,

        @NotNull(message = "O ID do cliente é obrigatório")
        Integer idCliente,

        @NotNull(message = "O ID do agendamento é obrigatório")
        Integer idAgendamento
) {}