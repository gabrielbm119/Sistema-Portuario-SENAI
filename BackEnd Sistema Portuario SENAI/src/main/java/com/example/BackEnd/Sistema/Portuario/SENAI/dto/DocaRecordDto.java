package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DocaRecordDto(
        @NotBlank(message = "O nome da doca é obrigatório")
        String nmDoca,

        @NotNull(message = "O comprimento é obrigatório")
        @Positive(message = "O comprimento deve ser positivo")
        Double cmDoca
) {}