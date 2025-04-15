package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBADMIN")
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String nmCliente; //nome
    private String emCliente; //email
    private String snCliente; //senha
    private String cdAdmin; //credencial

    // Getters e Setters
    public Integer getIdCliente() { return idCliente; }

    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNmCliente() { return nmCliente; }

    public void setNmCliente(String nmCliente) { this.nmCliente = nmCliente; }

    public String getEmCliente() { return emCliente; }

    public void setEmCliente(String emCliente) { this.emCliente = emCliente; }

    public String getSnCliente() { return snCliente; }

    public void setSnCliente(String snCliente) { this.snCliente = snCliente; }

    public String getCdAdmin() { return cdAdmin; }

    public void setCdAdmin(String cdAdmin) { this.cdAdmin = cdAdmin; }
}