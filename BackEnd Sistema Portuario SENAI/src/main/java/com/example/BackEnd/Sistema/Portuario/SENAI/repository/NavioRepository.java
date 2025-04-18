package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.NavioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NavioRepository extends JpaRepository<NavioModel, Integer> {
    Optional<NavioModel> findByIdNavio(int idNavio);
    Optional<NavioModel> findAllByIdNavio(int idNavio);
}