package com.example.BackEnd.Sistema.Portuario.SENAI.models;

import com.example.BackEnd.Sistema.Portuario.SENAI.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBUSUARIO")
public abstract class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nmUsuario; //nome
    private String emUsuario; //email
    private String snUsuario; //senha

    @Enumerated(EnumType.STRING)
    private TipoUsuario tpUsuario;
}