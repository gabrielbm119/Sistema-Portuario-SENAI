package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBCLIENTE")
public class ClienteModel extends UsuarioModel {

    private String pjCliente;
    private String tfCliente;
    //private List<Navio> navios;
    //private List<Debitos> debitos;

    @OneToOne(cascade = CascadeType.ALL)
    private CarteiraModel ctCliente;

    // Getters e Setters
}