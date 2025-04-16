package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteDebitoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteDebitoRepository extends JpaRepository<ClienteDebitoModel, Integer> {
    Optional<ClienteDebitoModel> findById(int Id);
    Optional<ClienteDebitoModel> findAllById(int Id);
}