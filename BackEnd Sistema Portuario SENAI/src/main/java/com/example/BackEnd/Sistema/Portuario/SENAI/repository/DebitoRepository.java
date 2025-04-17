package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.DebitoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebitoRepository extends JpaRepository<DebitoModel, Integer> {
    Optional<DebitoModel> findByIdDebito(int idDebito);
    Optional<DebitoModel> findAllByIdDebito(int idDebito);
}