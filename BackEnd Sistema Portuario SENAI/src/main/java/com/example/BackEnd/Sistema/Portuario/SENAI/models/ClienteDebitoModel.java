package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBCLIENTEDEBITO")
public class ClienteDebitoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClienteDebito;

    @ManyToOne
    @JoinColumn(name = "idCliente") // cria a FK no banco
    private ClienteModel clClienteDebito; //cliente

    @ManyToOne
    @JoinColumn(name = "idDebito") // cria a FK no banco
    private DebitoModel dbClienteDebito; //debito

    // Getters e Setters
    public Integer getIdClienteDebito() { return idClienteDebito; }

    public void setIdClienteDebito(Integer idClienteDebito) { this.idClienteDebito = idClienteDebito; }

    public ClienteModel getClClienteDebito() { return clClienteDebito; }

    public void setClClienteDebito(ClienteModel clClienteDebito) { this.clClienteDebito = clClienteDebito; }

    public DebitoModel getDbClienteDebito() { return dbClienteDebito; }

    public void setDbClienteDebito(DebitoModel dbClienteDebito) { this.dbClienteDebito = dbClienteDebito; }
}
