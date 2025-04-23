package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusAgendamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TBAGENDAMENTO")
public class AgendamentoModel {
    @Id @Column(name = "idAgendamento") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;
    private LocalDate dgAgendamento; //data geração
    private String hgAgendamento; //horário geração
    private LocalDate diAgendamento; //data início
    private String hiAgendamento; //horário início
    private LocalDate dfAgendamento; //data final
    private String hfAgendamento; //horário final

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

    public LocalDate getDgAgendamento() { return dgAgendamento; }

    public void setDgAgendamento(LocalDate dgAgendamento) { this.dgAgendamento = dgAgendamento; }

    public String getHgAgendamento() { return hgAgendamento; }

    public void setHgAgendamento(String hgAgendamento) { this.hgAgendamento = hgAgendamento; }

    public LocalDate getDiAgendamento() { return diAgendamento; }

    public void setDiAgendamento(LocalDate diAgendamento) { this.diAgendamento = diAgendamento; }

    public String getHiAgendamento() { return hiAgendamento; }

    public void setHiAgendamento(String hiAgendamento) { this.hiAgendamento = hiAgendamento; }

    public LocalDate getDfAgendamento() { return dfAgendamento; }

    public void setDfAgendamento(LocalDate dfAgendamento) { this.dfAgendamento = dfAgendamento; }

    public String getHfAgendamento() { return hfAgendamento; }

    public void setHfAgendamento(String hfAgendamento) { this.hfAgendamento = hfAgendamento; }

    public DocaModel getDcAgendamento() { return dcAgendamento; }

    public void setDcAgendamento(DocaModel dcAgendamento) { this.dcAgendamento = dcAgendamento; }

    public NavioModel getNvAgendamento() { return nvAgendamento; }

    public void setNvAgendamento(NavioModel nvAgendamento) { this.nvAgendamento = nvAgendamento; }

    public StatusAgendamento getStAgentamento() { return stAgentamento; }

    public void setStAgentamento(StatusAgendamento stAgentamento) { this.stAgentamento = stAgentamento; }

    public static String timeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(time);
    }
}