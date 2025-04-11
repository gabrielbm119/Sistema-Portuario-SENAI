package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBNAVIO")
public class NavioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNavio;
    private String nmNavio; //nome
    private String tpNavio; //tipo
    private Double cmNavio; //comprimento
    //private Cliente clNavio; //cliente
    //private List<Agendamento> agendamentos;

    // Getters e Setters
}