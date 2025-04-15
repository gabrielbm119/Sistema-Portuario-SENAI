package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBCLIENTE")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String nmCliente; //nome
    private String emCliente; //email
    private String snCliente; //senha
    private String pjCliente; //CNPJ
    private String tfCliente; //telefone

    // Getters e Setters
    public Integer getIdCliente() { return idCliente; }

    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNmCliente() { return nmCliente; }

    public void setNmCliente(String nmCliente) { this.nmCliente = nmCliente; }

    public String getEmCliente() { return emCliente; }

    public void setEmCliente(String emCliente) { this.emCliente = emCliente; }

    public String getSnCliente() { return snCliente; }

    public void setSnCliente(String snCliente) { this.snCliente = snCliente; }

    public String getPjCliente() { return pjCliente; }

    public void setPjCliente(String pjCliente) { this.pjCliente = pjCliente; }

    public String getTfCliente() { return tfCliente; }

    public void setTfCliente(String tfCliente) { this.tfCliente = tfCliente; }
}