package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBDOCA")
public class DocaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoca;
    private String nmDoca; //nome
    private Double cmDoca; //comprimento
    //private List<Agendamento> agendamentos;

    // Getters e Setters
}