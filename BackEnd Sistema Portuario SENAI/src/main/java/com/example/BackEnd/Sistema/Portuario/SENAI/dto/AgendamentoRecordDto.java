package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRecordDto(
        @NotNull(message = "A data de início é obrigatória")
        @Future(message = "A data de início deve estar no futuro")
        LocalDateTime diAgendamento,

        @NotNull(message = "A data de fim é obrigatória")
        @Future(message = "A data de fim deve estar no futuro")
        LocalDateTime dfAgendamento,

        @NotNull(message = "O ID da doca é obrigatório")
        Integer idDoca,

        @NotNull(message = "O ID do navio é obrigatório")
        Integer idNavio
) {}