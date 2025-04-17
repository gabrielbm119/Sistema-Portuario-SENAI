package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBNAVIO")
public class NavioModel {
    @Id @Column(name = "idNavio") // nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNavio;
    private String nmNavio; //nome
    private String tpNavio; //tipo
    private Double cmNavio; //comprimento

    @ManyToOne
    @JoinColumn(name = "idCliente") // cria a FK no banco
    private ClienteModel clNavio; //cliente

    // Getters e Setters
    public Integer getIdNavio() { return idNavio; }

    public void setIdNavio(Integer idNavio) { this.idNavio = idNavio; }

    public String getNmNavio() { return nmNavio; }

    public void setNmNavio(String nmNavio) { this.nmNavio = nmNavio; }

    public String getTpNavio() { return tpNavio; }

    public void setTpNavio(String tpNavio) { this.tpNavio = tpNavio; }

    public Double getCmNavio() { return cmNavio; }

    public void setCmNavio(Double cmNavio) { this.cmNavio = cmNavio; }

    public ClienteModel getClNavio() { return clNavio; }

    public void setClNavio(ClienteModel clNavio) { this.clNavio = clNavio; }
}