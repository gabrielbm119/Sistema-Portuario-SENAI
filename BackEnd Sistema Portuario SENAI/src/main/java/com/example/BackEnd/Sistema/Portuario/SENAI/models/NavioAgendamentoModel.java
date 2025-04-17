package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBNAVIOAGENDAMENTO")
public class NavioAgendamentoModel {
    @Id @Column(name = "idNavioAgendamento") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNavioAgendamento;

    @ManyToOne
    @JoinColumn(name = "idNavio") // cria a FK no banco
    private NavioModel nvNavioAgendamento; //navio

    @ManyToOne
    @JoinColumn(name = "idAgendamento") // cria a FK no banco
    private AgendamentoModel agNavioAgendamento; //agendamento

    // Getters e Setters
    public Integer getIdNavioAgendamento() { return idNavioAgendamento; }

    public void setIdNavioAgendamento(Integer idNavioAgendamento) { this.idNavioAgendamento = idNavioAgendamento; }

    public NavioModel getNvNavioAgendamento() { return nvNavioAgendamento; }

    public void setNvNavioAgendamento(NavioModel nvNavioAgendamento) { this.nvNavioAgendamento = nvNavioAgendamento; }

    public AgendamentoModel getAgNavioAgendamento() { return agNavioAgendamento; }

    public void setAgNavioAgendamento(AgendamentoModel agNavioAgendamento) { this.agNavioAgendamento = agNavioAgendamento; }
}