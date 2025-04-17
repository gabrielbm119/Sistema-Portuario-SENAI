package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteDebitoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteDebitoRepository extends JpaRepository<ClienteDebitoModel, Integer> {
    Optional<ClienteDebitoModel> findByIdClienteDebito(int idClienteDebito);
    Optional<ClienteDebitoModel> findAllByIdClienteDebito(int idClienteDebito);
}