package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TBCARTEIRA")
public class CarteiraModel {
    @Id @Column(name = "idCarteira") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarteira;
    private BigDecimal sdCarteira; //saldo

    @OneToOne
    @JoinColumn(name = "idCliente") // cria a FK no banco
    private ClienteModel clCarteira; //cliente

    // Getters e Setters
    public Integer getIdCarteira() { return idCarteira; }

    public void setIdCarteira(Integer idCarteira) { this.idCarteira = idCarteira; }

    public BigDecimal getSdCarteira() { return sdCarteira; }

    public void setSdCarteira(BigDecimal sdCarteira) { this.sdCarteira = sdCarteira; }

    public ClienteModel getClCarteira() { return clCarteira; }

    public void setClCarteira(ClienteModel clCarteira) { this.clCarteira = clCarteira; }
}