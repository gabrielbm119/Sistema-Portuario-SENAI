package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TBCARTEIRA")
public class CarteiraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarteira;
    private BigDecimal sdCarteira;
    //private Cliente clCarteira; //cliente

    // Getters e Setters
}