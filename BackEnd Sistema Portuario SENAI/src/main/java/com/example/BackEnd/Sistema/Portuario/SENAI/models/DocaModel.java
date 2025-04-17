package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBDOCA")
public class DocaModel {
    @Id @Column(name = "idDoca") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoca;
    private String nmDoca; //nome
    private Double cmDoca; //comprimento

    // Getters e Setters

    public Integer getIdDoca() { return idDoca; }

    public void setIdDoca(Integer idDoca) { this.idDoca = idDoca; }

    public String getNmDoca() { return nmDoca; }

    public void setNmDoca(String nmDoca) { this.nmDoca = nmDoca; }

    public Double getCmDoca() { return cmDoca; }

    public void setCmDoca(Double cmDoca) { this.cmDoca = cmDoca; }
}