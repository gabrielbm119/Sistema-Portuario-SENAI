package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusDebito;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DebitoRecordDto(
        @NotNull(message = "O valor do débito é obrigatório")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal vlDebito,

        LocalDate dpDebito,
        String hpDebito,

        @NotNull(message = "A data de validade é obrigatória")
        LocalDate dvDebito,

        @NotNull(message = "O ID do agendamento é obrigatório")
        Integer idAgendamento,

        StatusDebito statusDebito
) {}