package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBCLIENTENAVIO")
public class ClienteNavioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClienteNavio;

    @ManyToOne
    @JoinColumn(name = "idCliente") // cria a FK no banco
    private ClienteModel clClienteNavio; //cliente

    @ManyToOne
    @JoinColumn(name = "idNavio") // cria a FK no banco
    private NavioModel nvClienteNavio; //navio

    // Getters e Setters
    public Integer getIdClienteNavio() { return idClienteNavio; }

    public void setIdClienteNavio(Integer idClienteNavio) { this.idClienteNavio = idClienteNavio; }

    public ClienteModel getClClienteNavio() { return clClienteNavio; }

    public void setClClienteNavio(ClienteModel clClienteNavio) { this.clClienteNavio = clClienteNavio; }

    public NavioModel getNvClienteNavio() { return nvClienteNavio; }

    public void setNvClienteNavio(NavioModel nvClienteNavio) { this.nvClienteNavio = nvClienteNavio; }
}
