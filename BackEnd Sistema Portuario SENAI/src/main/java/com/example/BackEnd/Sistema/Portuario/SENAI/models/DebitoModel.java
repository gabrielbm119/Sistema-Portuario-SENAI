package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusDebito;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "TBDEBITO")
public class DebitoModel {
    @Id @Column(name = "idDebito") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDebito;
    private BigDecimal vlDebito; //valor
    private LocalDate dgDebito; //data geração
    private String hgDebito; //horário geração
    private LocalDate dpDebito; //data pagamento
    private String hpDebito; //horário pagamento
    private LocalDate dvDebito; //data validade

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

    public LocalDate getDgDebito() { return dgDebito; }

    public void setDgDebito(LocalDate dgDebito) { this.dgDebito = dgDebito; }

    public String getHgDebito() { return hgDebito; }

    public void setHgDebito(String hgDebito) { this.hgDebito = hgDebito; }

    public LocalDate getDpDebito() { return dpDebito; }

    public void setDpDebito(LocalDate dpDebito) { this.dpDebito = dpDebito; }

    public String getHpDebito() { return hpDebito; }

    public void setHpDebito(String hpDebito) { this.hpDebito = hpDebito; }

    public LocalDate getDvDebito() { return dvDebito; }

    public void setDvDebito(LocalDate dvDebito) { this.dvDebito = dvDebito; }

    public ClienteModel getClDebito() { return clDebito; }

    public void setClDebito(ClienteModel clDebito) { this.clDebito = clDebito; }

    public AgendamentoModel getAgDebito() { return agDebito; }

    public void setAgDebito(AgendamentoModel agDebito) { this.agDebito = agDebito; }

    public StatusDebito getStDebito() { return stDebito; }

    public void setStDebito(StatusDebito stDebito) { this.stDebito = stDebito; }

    public static String timeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(time);
    }
}