package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteNavioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteNavioRepository extends JpaRepository<ClienteNavioModel, Integer> {
    Optional<ClienteNavioModel> findByNvClienteNavio_IdNavio(Integer idNavio);
}