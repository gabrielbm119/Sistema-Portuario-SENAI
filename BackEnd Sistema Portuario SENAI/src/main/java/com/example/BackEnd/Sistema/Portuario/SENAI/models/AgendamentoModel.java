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

    @ManyToOne
    @JoinColumn(name = "idDoca") // cria a FK no banco
    private DocaModel dcAgendamento; //doca

    @ManyToOne
    @JoinColumn(name = "idNavio") // cria a FK no banco
    private NavioModel nvAgendamento; //navio

    @Enumerated(EnumType.STRING)
    private StatusAgendamento stAgentamento;

    // Getters e Setters
    public Integer getIdAgendamento() { return idAgendamento; }

    public void setIdAgendamento(Integer idAgendamento) { this.idAgendamento = idAgendamento; }

    public LocalDateTime getDgAgendamento() { return dgAgendamento; }

    public void setDgAgendamento(LocalDateTime dgAgendamento) { this.dgAgendamento = dgAgendamento; }

    public LocalDateTime getDiAgendamento() { return diAgendamento; }

    public void setDiAgendamento(LocalDateTime diAgendamento) { this.diAgendamento = diAgendamento; }

    public LocalDateTime getDfAgendamento() { return dfAgendamento; }

    public void setDfAgendamento(LocalDateTime dfAgendamento) { this.dfAgendamento = dfAgendamento; }

    public DocaModel getDcAgendamento() { return dcAgendamento; }

    public void setDcAgendamento(DocaModel dcAgendamento) { this.dcAgendamento = dcAgendamento; }

    public NavioModel getNvAgendamento() { return nvAgendamento; }

    public void setNvAgendamento(NavioModel nvAgendamento) { this.nvAgendamento = nvAgendamento; }

    public StatusAgendamento getStAgentamento() { return stAgentamento; }

    public void setStAgentamento(StatusAgendamento stAgentamento) { this.stAgentamento = stAgentamento; }
}