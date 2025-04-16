package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
    Optional<ClienteModel> findById(int Id);
    Optional<ClienteModel> findAllById(int Id);
}