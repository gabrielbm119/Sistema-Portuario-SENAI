package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBADMIN")
public class AdminModel {

    @Id @Column(name = "idAdmin") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;
    private String nmAdmin; //nome
    private String emAdmin; //email
    private String snAdmin; //senha
    private String cdAdmin; //credencial

    // Getters e Setters
    public Integer getIdAdmin() { return idAdmin; }

    public void setIdAdmin(Integer idAdmin) { this.idAdmin = idAdmin; }

    public String getNmAdmin() { return nmAdmin; }

    public void setNmAdmin(String nmAdmin) { this.nmAdmin = nmAdmin; }

    public String getEmAdmin() { return emAdmin; }

    public void setEmAdmin(String emAdmin) { this.emAdmin = emAdmin; }

    public String getSnAdmin() { return snAdmin; }

    public void setSnAdmin(String snAdmin) { this.snAdmin = snAdmin; }

    public String getCdAdmin() { return cdAdmin; }

    public void setCdAdmin(String cdAdmin) { this.cdAdmin = cdAdmin; }
}