package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NavioRecordDto(
        @NotBlank(message = "O nome do navio é obrigatório")
        String nmNavio,

        @NotBlank(message = "O tipo do navio é obrigatório")
        String tpNavio,

        @NotNull(message = "O comprimento é obrigatório")
        @Positive(message = "O comprimento deve ser positivo")
        Double cmNavio,

        @NotNull(message = "O ID do cliente é obrigatório")
        Integer idCliente
) {}