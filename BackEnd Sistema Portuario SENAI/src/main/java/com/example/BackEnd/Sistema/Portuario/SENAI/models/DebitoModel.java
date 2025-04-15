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
    private BigDecimal vlDebito; //valor
    private LocalDateTime dgDebito; //data geração
    private LocalDateTime dpDebito; //data pagamento
    private LocalDateTime dvDebito; //data validade

    @ManyToOne
    @JoinColumn(name = "idCliente") // cria a FK no banco
    private ClienteModel clDebito; //cliente

    @ManyToOne
    @JoinColumn(name = "idAgendamento") // cria a FK no banco
    private AgendamentoModel agDebito; //agendamento

    @Enumerated(EnumType.STRING)
    private StatusDebito stDebito;

    // Getters e Setters
    public Integer getIdDebito() { return idDebito; }

    public void setIdDebito(Integer idDebito) { this.idDebito = idDebito; }

    public BigDecimal getVlDebito() { return vlDebito; }

    public void setVlDebito(BigDecimal vlDebito) { this.vlDebito = vlDebito; }

    public LocalDateTime getDgDebito() { return dgDebito; }

    public void setDgDebito(LocalDateTime dgDebito) { this.dgDebito = dgDebito; }

    public LocalDateTime getDpDebito() { return dpDebito; }

    public void setDpDebito(LocalDateTime dpDebito) { this.dpDebito = dpDebito; }

    public LocalDateTime getDvDebito() { return dvDebito; }

    public void setDvDebito(LocalDateTime dvDebito) { this.dvDebito = dvDebito; }

    public ClienteModel getClDebito() { return clDebito; }

    public void setClDebito(ClienteModel clDebito) { this.clDebito = clDebito; }

    public AgendamentoModel getAgDebito() { return agDebito; }

    public void setAgDebito(AgendamentoModel agDebito) { this.agDebito = agDebito; }

    public StatusDebito getStDebito() { return stDebito; }

    public void setStDebito(StatusDebito stDebito) { this.stDebito = stDebito; }
}