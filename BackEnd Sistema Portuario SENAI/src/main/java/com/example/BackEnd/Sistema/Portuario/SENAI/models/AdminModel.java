package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBADMIN")
public class AdminModel extends UsuarioModel {

    private String cdAdmin;

    // Getters e Setters
}