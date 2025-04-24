package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusAgendamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendamentoRecordDto(
        @NotNull(message = "A data de início é obrigatória")
        @Future(message = "A data de início deve estar no futuro")
        LocalDate diAgendamento,

        @NotNull(message = "O horário de início é obrigatório")
        String hiAgendamento,

        @NotNull(message = "A data de fim é obrigatória")
        @Future(message = "A data de fim deve estar no futuro")
        LocalDate dfAgendamento,

        @NotNull(message = "O horário de fim é obrigatório")
        String hfAgendamento,

        @NotNull(message = "O ID da doca é obrigatório")
        Integer idDoca,

        @NotNull(message = "O ID do navio é obrigatório")
        Integer idNavio,

        StatusAgendamento stAgendamento
) {}