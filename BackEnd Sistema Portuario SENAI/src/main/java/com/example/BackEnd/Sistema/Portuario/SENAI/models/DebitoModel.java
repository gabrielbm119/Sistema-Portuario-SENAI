package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusDebito;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBDEBITO")
public class DebitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDebito;
    private BigDecimal vlDebito;
    private LocalDateTime dgDebito; //data geração
    private LocalDateTime dpDebito; //data pagamento
    private LocalDateTime dvDebito; //data validade
    //private ClienteModel clDebito; //cliente
    //private AgendamentoModel agDebito; //agendamento

    @Enumerated(EnumType.STRING)
    private StatusDebito stDebito;

    // Getters e Setters
}