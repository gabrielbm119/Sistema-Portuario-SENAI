package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBDOCAAGENDAMENTO")
public class DocaAgendamentoModel {
    @Id @Column(name = "idDocaAgendamento") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocaAgendamento;

    @ManyToOne
    @JoinColumn(name = "idDoca") // cria a FK no banco
    private DocaModel dcDocaAgendamento; //doca

    @ManyToOne
    @JoinColumn(name = "idAgendamento") // cria a FK no banco
    private AgendamentoModel agDocaAgendamento; //agendamento

    // Getters e Setters
    public Integer getIdDocaAgendamento() { return idDocaAgendamento; }

    public void setIdDocaAgendamento(Integer idDocaAgendamento) { this.idDocaAgendamento = idDocaAgendamento; }

    public DocaModel getDcDocaAgendamento() { return dcDocaAgendamento; }

    public void setDcDocaAgendamento(DocaModel dcDocaAgendamento) { this.dcDocaAgendamento = dcDocaAgendamento; }

    public AgendamentoModel getAgDocaAgendamento() { return agDocaAgendamento; }

    public void setAgDocaAgendamento(AgendamentoModel agDocaAgendamento) { this.agDocaAgendamento = agDocaAgendamento; }
}