package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusAgendamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBAGENDAMENTO")
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;
    private LocalDateTime dgAgendamento; //data geração
    private LocalDateTime diAgendamento; //data início
    private LocalDateTime dfAgendamento; //data final
    //private DocaModel dcAgendamento; //doca
    //private NavioModel nvAgendamento; //navio

    @Enumerated(EnumType.STRING)
    private StatusAgendamento stAgentamento;

    // Getters e Setters
}