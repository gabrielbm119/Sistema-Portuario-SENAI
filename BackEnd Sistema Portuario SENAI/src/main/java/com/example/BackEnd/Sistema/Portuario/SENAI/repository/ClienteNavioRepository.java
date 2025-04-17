package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteNavioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteNavioRepository extends JpaRepository<ClienteNavioModel, Integer> {
    Optional<ClienteNavioModel> findByIdClienteNavio(int idClienteNavio);
    Optional<ClienteNavioModel> findAllByIdClienteNavio(int idClienteNavio);
}