package com.example.BackEnd.Sistema.Portuario.SENAI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRecordDto(
        @NotBlank(message = "O nome é obrigatório")
        String nmCliente,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String emCliente,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String snCliente,

        @NotBlank(message = "O CNPJ é obrigatório")
        String pjCliente,

        @NotBlank(message = "O telefone é obrigatório")
        String tfCliente
) {}